package com.icommerce.iorder.service;

import com.icommerce.iorder.model.Order;
import com.icommerce.iorder.model.OrderRequest;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order store(OrderRequest request);
}
