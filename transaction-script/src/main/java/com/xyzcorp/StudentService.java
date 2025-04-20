package com.xyzcorp;


import java.sql.*;
import java.util.function.Supplier;

public class StudentService {
    private final Supplier<Connection> source;

    public StudentService(Supplier<Connection> source) {
        this.source = source;
    }

    public Long persist(Student student) {
        try (var connection = source.get()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO REGISTRATION (FIRSTNAME, LASTNAME, STUDENTID) VALUES (?, ?, ?);",
                         Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, student.firstName());
                preparedStatement.setString(2, student.lastName());
                preparedStatement.setString(3, student.studentId().value());
                preparedStatement.execute();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    connection.commit();
                    return generatedKeys.getLong(1);
                }

                connection.rollback();
                return -1L;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student findByStudentId(StudentId studentId) {
        try (var connection = source.get();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT FIRSTNAME, LASTNAME, STUDENTID FROM REGISTRATION WHERE STUDENTID = ?")) {
            preparedStatement.setString(1, studentId.value());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(
                        new StudentId(resultSet.getString("STUDENTID")),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("LASTNAME")
                    );
                } else {
                    throw new RuntimeException("Student not found for ID: " + studentId.value());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving student by ID: " + studentId.value(), e);
        }
    }
}
