package com.icommerce.iproduct.service;

import com.icommerce.iproduct.model.Product;
import com.icommerce.iproduct.model.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Page<Product> search(String criteria, String columnToSort);

    Product findById(Long id);

    Product save(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);

}
