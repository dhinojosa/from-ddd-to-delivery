package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class StudentServiceTest {

    @Container
    private static final PostgreSQLContainer<?> postgresSQLContainer =
        new PostgreSQLContainer<>("postgres:14.5")
            .withInitScript("import.sql")
            .withDatabaseName("school");
    private static PGConnectionPoolDataSource source;

    @BeforeEach
    void setUp() {
        source = new PGConnectionPoolDataSource();
        source.setUser(postgresSQLContainer.getUsername());
        source.setPassword(postgresSQLContainer.getPassword());
        source.setUrl(postgresSQLContainer.getJdbcUrl());
        source.setDatabaseName(postgresSQLContainer.getDatabaseName());
        System.out.println("Starting Container!");
        System.out.printf("Username: %s%n", postgresSQLContainer.getUsername());
        System.out.printf("Password: %s%n", postgresSQLContainer.getPassword());
        System.out.printf("URL: %s%n", postgresSQLContainer.getJdbcUrl());
        System.out.printf("Database: %s%n", postgresSQLContainer.getDatabaseName());
    }

    @Test
    void testCanary() {
        assertThat(true).isTrue();
    }


    @Test
    void testInsert() {
        StudentService studentService = new StudentService(() -> {
            try {
                return source.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        Long id = studentService.persist(new Student(new StudentId("3020-1202"), "Freida", "Township"));
        assertThat(id).isNotNull();
        assertThat(id).isGreaterThanOrEqualTo(0);
        Long id2 = studentService.persist(new Student(new StudentId("3020-1203"), "Frankin", "Placido"));
        assertThat(id2).isNotNull();
        assertThat(id2).isGreaterThanOrEqualTo(0);
    }

    @Test
    void testFindByStudentId() {
        StudentService studentService = new StudentService(() -> {
            try {
                return source.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        StudentId studentId = new StudentId("3020-1202");
        studentService.persist(new Student(studentId, "Freida", "Township"));

        Student student = studentService.findByStudentId(studentId);
        assertThat(student.firstName()).isEqualTo("Freida");
    }
}
