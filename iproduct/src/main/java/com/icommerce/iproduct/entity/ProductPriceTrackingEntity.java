package com.icommerce.iproduct.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "product_price_tracking")
public class ProductPriceTrackingEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "productTrackingId", unique = true, nullable = false)
    private Long id;

    @Column(name = "productId")
    private Long productId;

    private Double price;

    private Date effectiveDate;
}
