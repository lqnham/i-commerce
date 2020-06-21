package com.icommerce.iproduct.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "productId", unique = true, nullable = false)
    private Long productId;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "branch")
    private String branch;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "delete", columnDefinition = "boolean default false")
    private boolean delete;
}
