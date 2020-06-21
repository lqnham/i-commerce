package com.icommerce.iorder.dao;

import com.icommerce.iorder.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<OrderEntity, Long> {
}
