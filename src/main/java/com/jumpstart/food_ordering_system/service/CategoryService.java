package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import java.util.List;

/**
 * CategoryService defines the contract (interface) for the category business logic.
 *
 * Using an interface here follows good software design principles — it separates
 * WHAT the service does from HOW it does it.
 * The actual implementation is in CategoryServiceImpl.java.
 */
public interface CategoryService {

    /**
     * Retrieves all categories from the database
     * and returns them as a list of CategoryDto objects.
     *
     * @return List of CategoryDto
     */
    List<CategoryDto> getAllCategories();
}
