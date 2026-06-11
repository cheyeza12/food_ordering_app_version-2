package com.jumpstart.food_ordering_system.entity;

import jakarta.persistence.*;

/**
 * Category is an Entity class that represents the 'category' table in the database.
 * Each instance of this class maps to a single row in the category table.
 * Spring Data JPA uses this class to read and write category data automatically.
 */
@Entity
@Table(name = "category")
public class Category {

    // @Id marks this field as the primary key of the table
    // @GeneratedValue tells JPA to auto-increment the id value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Default constructor required by JPA
    public Category() {}

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
