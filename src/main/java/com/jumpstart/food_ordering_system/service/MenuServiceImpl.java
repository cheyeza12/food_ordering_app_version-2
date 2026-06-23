package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.entity.Menu;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import com.jumpstart.food_ordering_system.repository.MenuRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));
        Menu saved = menuRepository.save(mapToEntity(dto, category));
        return Response.success("Menu created successfully", mapToDto(saved));
    }

    @Override
    public Response<Page<MenuDto>> getAllMenus(Long categoryId, String search, Pageable pageable) {
        Specification<Menu> spec = Specification.where(null);

        if (categoryId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category").get("id"), categoryId));
        }

        if (search != null && !search.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
        }

        Page<MenuDto> page = menuRepository.findAll(spec, pageable).map(this::mapToDto);
        return Response.success("Menus retrieved successfully", page);
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));
        return Response.success("Menu retrieved successfully", mapToDto(menu));
    }

    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {
        Menu existing = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setImageUrl(dto.getImageUrl());
        existing.setCategory(category);

        return Response.success("Menu updated successfully", mapToDto(menuRepository.save(existing)));
    }

    @Override
    public Response<Void> deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));
        menuRepository.delete(menu);
        return Response.success("Menu deleted successfully", null);
    }

    // ── Private Mappers ──────────────────────────────────────────

    private MenuDto mapToDto(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .categoryId(menu.getCategory().getId())
                .categoryName(menu.getCategory().getName())
                .build();
    }

    private Menu mapToEntity(MenuDto dto, Category category) {
        return Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }
}