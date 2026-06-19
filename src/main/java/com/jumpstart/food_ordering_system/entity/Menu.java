package com.jumpstart.food_ordering_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "menus")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private BigDecimal price;

    private String imageUrl;

    // Many menu items can belong to one category.
    // @JoinColumn tells Hibernate to store the relationship as a
    // "category_id" foreign key column on the "menus" table.
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
