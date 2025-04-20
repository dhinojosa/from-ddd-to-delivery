package com.xyzcorp;


import io.ebean.Database;

public class StudentRepository {
    private final Database database;

    public StudentRepository(Database database) {
        this.database = database;
    }

    public Student findByStudentId(StudentId studentId) {
         return database.find(Student.class)
             .where()
             .eq("studentId.value", studentId.value())
             .findOne();
    }

    public Long persist(Student student) {
        database.save(student);
        return student.getId();
    }
}
