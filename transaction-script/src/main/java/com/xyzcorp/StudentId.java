package com.xyzcorp;


public record StudentId(String value) {
    public StudentId {
        if (!value.matches("\\d{4}-\\d{4}")) {
            throw new IllegalArgumentException("StudentId must be in format XXXX-XXXX");
        }
    }
}
