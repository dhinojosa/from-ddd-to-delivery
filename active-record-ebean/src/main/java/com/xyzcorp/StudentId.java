package com.xyzcorp;

import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.StringJoiner;

//Value Object
@Embeddable
public class StudentId {
    private long value;

    protected StudentId() {
        // Required by JPA/Ebean
    }

    public StudentId(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentId studentId = (StudentId) o;
        return value == studentId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StudentId.class.getSimpleName() + "[", "]")
            .add("value=" + value)
            .toString();
    }
}
