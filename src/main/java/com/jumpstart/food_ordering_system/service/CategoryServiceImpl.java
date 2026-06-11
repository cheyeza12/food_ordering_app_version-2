package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
