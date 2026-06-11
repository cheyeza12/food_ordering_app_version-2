package com.jumpstart.food_ordering_system.repository;

import com.jumpstart.food_ordering_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository is responsible for all database operations related to Category.
 *
 * By extending JpaRepository, Spring automatically provides common database methods
 * such as findAll(), findById(), save(), and deleteById() — without writing any SQL.
 *
 * The two generic types are:
 * - Category: the entity this repository manages
 * - Long: the data type of the primary key (id)
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // No additional methods needed for now.
    // JpaRepository already provides findAll() which we will use
    // to retrieve all categories from the database.
}
