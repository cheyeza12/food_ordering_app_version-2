package com.jumpstart.food_ordering_system.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;
    private String description;
    // + getter and setter
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
