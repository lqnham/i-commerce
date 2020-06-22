package com.icommerce.iorder.service.impl;

import com.icommerce.iorder.dao.OrderDAO;
import com.icommerce.iorder.entity.OrderEntity;
import com.icommerce.iorder.model.Order;
import com.icommerce.iorder.model.OrderDetail;
import com.icommerce.iorder.service.OrderService;
import com.icommerce.iorder.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;

    @Autowired
    public OrderServiceImpl(OrderDAO dao) {
        this.dao = dao;

    }

    @Override
    public List<Order> findAll() {
        ModelMapper mapper = new ModelMapper();
        List<Order> list = new ArrayList<>();
        dao.findAll().stream().forEach(entity -> {
            list.add(mapper.map(entity, Order.class));
        });
        return list;
    }

    @Override
    public Order store(OrderRequest request) {
        ModelMapper mapper = new ModelMapper();
        Set<OrderDetail> details = request.getDetails();
        double sum = details.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        OrderEntity entity = mapper.map(request, OrderEntity.class);
        entity.setSumPrice(sum);
        return mapper.map(dao.save(entity), Order.class);
    }
}
