package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CategoryController handles all incoming HTTP requests related to categories.
 *
 * The controller is the entry point of the application from the client's perspective.
 * Its responsibilities are:
 * - Receive HTTP requests from the browser or Postman
 * - Call the appropriate service method
 * - Return the response back to the client (as JSON)
 *
 * @RestController combines @Controller and @ResponseBody —
 * it tells Spring this class handles REST API requests
 * and automatically converts return values to JSON.
 *
 * @RequestMapping("/api/category") sets the base URL for all endpoints in this controller.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // @Autowired injects CategoryService automatically (Dependency Injection)
    @Autowired
    private CategoryService categoryService;

    /**
     * GET /api/category
     *
     * This endpoint retrieves all categories from the database
     * and returns them as a JSON array.
     *
     * Example response:
     * [
     *   { "id": 1, "name": "Fast Food" },
     *   { "id": 2, "name": "Pizza" }
     * ]
     *
     * @return List of CategoryDto objects (automatically converted to JSON)
     */
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        System.out.println("is this application working");
        // Delegate to the service layer — controller should not contain business logic
        return categoryService.getAllCategories();
    }
}
