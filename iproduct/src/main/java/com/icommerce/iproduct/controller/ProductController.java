package com.icommerce.iproduct.controller;

import com.icommerce.iproduct.model.Product;
import com.icommerce.iproduct.model.ProductRequest;
import com.icommerce.iproduct.service.AuditClient;
import com.icommerce.iproduct.service.InterCommunicationService;
import com.icommerce.iproduct.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;


@RestController
@Slf4j
public class ProductController {
    private final ProductService productService;

    private final InterCommunicationService communicationService;

    @Autowired
    public ProductController(ProductService productService) {
        this.communicationService = InterCommunicationService.getInstance();
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        log.info("findAll");
        communicationService.triggerAudit("findAll", "findAll");
        return productService.findAll();
    }

    @GetMapping("/search")
    public Page<Product> search(@RequestParam(value = "search") String search, @RequestParam(value = "sortBy") String sortBy) {
        log.info("Search: " + search + "SortBy: "  + sortBy);
        communicationService.triggerAudit("search", search);
        return productService.search(search, sortBy);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        log.info("getProductById: " + id);
        communicationService.triggerAudit("getProductById", String.valueOf(id));
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product create(@Valid @RequestBody ProductRequest request) {
        log.info("creating: " + request.getName());
        return productService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Product update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        log.info("update for: " + id);
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("delete for: " + id);
        productService.delete(id);
    }
}
