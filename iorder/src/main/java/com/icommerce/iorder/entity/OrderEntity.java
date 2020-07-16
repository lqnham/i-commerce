package com.icommerce.iorder.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "iorder")
public class OrderEntity extends OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "ordered_at", columnDefinition = "TIMESTAMP", updatable = false)
    private Date date;

    private Double sumPrice;

    private Long customerId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity")
    private Set<OrderDetailEntity> orderDetails;
}
