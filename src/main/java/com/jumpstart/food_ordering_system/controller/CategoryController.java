package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/categories")
public class CategoryController {

    // @Autowired injects CategoryService automatically (Dependency Injection)
    @Autowired
    private CategoryService categoryService;

    /**
     * GET /api/categories
     *
     * This endpoint retrieves all categories from the database
     * and returns them wrapped in a standard Response<T>.
     *
     * @return Response containing a List of CategoryDto objects
     */
    @GetMapping
    public ResponseEntity<Response<List<CategoryDto>>> getAllCategories() {
        System.out.println("is this application working");
        // Delegate to the service layer — controller should not contain business logic
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(Response.success("Categories retrieved", categories));
    }

    // GET /api/categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(Response.success("Category retrieved", dto));
    }

    // POST /api/categories
    @PostMapping
    public ResponseEntity<Response<CategoryDto>> addCategory(@RequestBody @Valid CategoryDto dto) {
        CategoryDto created = categoryService.addCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Category created", created));
    }

    // PUT /api/categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto dto) {
        CategoryDto updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(Response.success("Category updated", updated));
    }

    // DELETE /api/categories/{id}
    // Kept as 204 No Content — no Response<T> wrapper here since 204 must not have a body.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
