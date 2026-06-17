package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * CategoryServiceImpl is the implementation of the CategoryService interface.
 *
 * The service layer sits between the Controller and the Repository.
 * Its responsibilities are:
 * - Fetch data from the repository (database)
 * - Apply any business logic if needed
 * - Convert Entity objects into DTO objects
 * - Return the result to the Controller
 *
 * @Service tells Spring to register this class as a service component
 * so it can be injected wherever CategoryService is needed.
 */
@Service


public class CategoryServiceImpl implements CategoryService {

    // @Autowired injects the CategoryRepository automatically by Spring (Dependency Injection)
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves all Category records from the database,
     * converts each one to a CategoryDto, and returns the list.
     *
     * @return List of CategoryDto objects
     */
    @Override
    public List<CategoryDto> getAllCategories() {

        // Step 1: Fetch all Category entities from the database
        List<Category> categories = categoryRepository.findAll();

        // Step 2: Create an empty list to hold the DTOs
        List<CategoryDto> categoryDtos = new ArrayList<>();

        // Step 3: Loop through each Category entity and convert it to a DTO
        for (Category category : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            categoryDtos.add(dto);
        }

        // Step 4: Return the list of DTOs to the controller
        return categoryDtos;
    }
    // Get a single category by id - throws exception if not found
    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + id));
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    // Create a new category - maps DTO to entity, saves, returns saved DTO
    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());  // ← set BEFORE save

        Category saved = categoryRepository.save(category);

        CategoryDto savedDto = new CategoryDto();
        savedDto.setId(saved.getId());
        savedDto.setName(saved.getName());
        savedDto.setDescription(saved.getDescription());  // ← map back to DTO
        return savedDto;
    }

    // Update existing category - find by id, update name, save, return updated DTO
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + id));
        category.setName(dto.getName());
        Category updated = categoryRepository.save(category);
        CategoryDto updatedDto = new CategoryDto();
        updatedDto.setId(updated.getId());
        updatedDto.setName(updated.getName());
        return updatedDto;
    }

    // Delete category by id - throws exception if not found
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + id));
        categoryRepository.delete(category);
    }}
