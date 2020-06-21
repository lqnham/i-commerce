package com.icommerce.iproduct.service;

import com.icommerce.iproduct.model.Product;
import com.icommerce.iproduct.model.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> search(String criteria);

    Product findById(Long id);

    Product save(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);

}
