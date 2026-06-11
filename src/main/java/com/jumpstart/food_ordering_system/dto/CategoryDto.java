package com.jumpstart.food_ordering_system.dto;

/**
 * CategoryDto (Data Transfer Object) is used to transfer category data
 * between the service layer and the controller layer.
 *
 * A DTO is a simple object that only contains data — no business logic.
 * It protects the internal structure of the Entity from being exposed
 * directly to the outside world (API consumers).
 *
 * Instead of sending the raw Category entity to the client,
 * we convert it to a CategoryDto first. This gives us control
 * over exactly what data gets sent in the API response.
 */
public class CategoryDto {

    private Long id;
    private String name;

    // Default constructor
    public CategoryDto() {}

    public CategoryDto(Long id, String name) {
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
