package com.xyzcorp;

import io.ebean.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import io.ebean.Database;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class StudentRepositoryTest {
    @Container
    private static final PostgreSQLContainer<?> postgresSQLContainer =
        new PostgreSQLContainer<>("postgres:14.5")
            .withInitScript("import.sql") // Optional if you need to pre-load data
            .withDatabaseName("school")
            .withUsername("testuser")
            .withPassword("testpassword");

    private Database database;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        System.out.println("Starting Container!");
        System.out.printf("Username: %s%n", postgresSQLContainer.getUsername());
        System.out.printf("Password: %s%n", postgresSQLContainer.getPassword());
        System.out.printf("URL: %s%n", postgresSQLContainer.getJdbcUrl());
        System.out.printf("Database: %s%n", postgresSQLContainer.getDatabaseName());

        // Configure Ebean to use Testcontainers database
        System.setProperty("datasource.default", "db");
        System.setProperty("datasource.db.url", postgresSQLContainer.getJdbcUrl());
        System.setProperty("datasource.db.username", postgresSQLContainer.getUsername());
        System.setProperty("datasource.db.password", postgresSQLContainer.getPassword());

        // Initialize Ebean database
        database = DB.getDefault();
        studentRepository = new StudentRepository(database);
    }

    @Test
    void testCanary() {
        assertThat(true).isTrue();
    }

    @Test
    void testPersistANewStudent() {
        StudentRepository studentRepository = new StudentRepository(database);
        Student student = new Student(new StudentId(20L), "Freida", "Township");
        var result = studentRepository.persist(student);
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    void testFindByStudentId() {
        StudentRepository studentRepository = new StudentRepository(database);
        StudentId studentId = new StudentId(20L);
        Student student = new Student(studentId, "Freida", "Township");
        studentRepository.persist(student);
        assertThat(studentRepository.findByStudentId(studentId).getFirstName()).isEqualTo("Freida");
    }
}
