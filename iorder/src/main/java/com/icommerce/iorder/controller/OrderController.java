package com.icommerce.iorder.controller;

import com.icommerce.iorder.model.Order;
import com.icommerce.iorder.service.OrderService;
import com.icommerce.iorder.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll")
    @ResponseBody
    public List<Order> findAll() {
        log.info("Order - findAll");
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Order store(@Valid @RequestBody OrderRequest request) {
        log.info("Order - was call By: ");
        return service.store(request);
    }

}
