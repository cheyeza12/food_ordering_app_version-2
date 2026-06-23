package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Response<MenuDto>> create(
            @RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<Response<Page<MenuDto>>> all(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(menuService.getAllMenus(categoryId, search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> byId(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.updateMenu(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.deleteMenu(id));
    }
}