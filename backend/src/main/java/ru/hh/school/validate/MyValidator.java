package ru.hh.school.validate;

import javax.inject.Singleton;

public class MyValidator {

    public static void validatePaginationProperties(int page, int perPage) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number must be greater than or equal to zero!");
        }
        if (perPage < 1) {
            throw new IllegalArgumentException("Page size must be greater than zero! ");
        }
        if (perPage > 100) {
            throw new IllegalArgumentException("Page size must be less than 100! ");
        }
    }

    public static void validateId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than or equal to zero!");
        }
    }
}
