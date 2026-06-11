package com.jumpstart.food_ordering_system.exception;

/**
 * CategoryNotFoundException is a custom exception class used when a
 * requested category cannot be found in the database.
 *
 * Custom exceptions make error handling more meaningful and specific.
 * Instead of throwing a generic RuntimeException, we throw a
 * CategoryNotFoundException so the caller knows exactly what went wrong.
 *
 * Extending RuntimeException means this is an unchecked exception —
 * it does not need to be declared or caught explicitly.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     * The message is passed up to the parent RuntimeException class.
     *
     * @param message Description of what went wrong
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
