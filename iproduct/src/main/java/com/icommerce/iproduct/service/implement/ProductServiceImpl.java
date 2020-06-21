package com.icommerce.iproduct.service.implement;

import com.icommerce.iproduct.dao.ProductDAO;
import com.icommerce.iproduct.dao.ProductTrackingDAO;
import com.icommerce.iproduct.model.Product;
import com.icommerce.iproduct.model.ProductRequest;
import com.icommerce.iproduct.entity.ProductEntity;
import com.icommerce.iproduct.entity.ProductPriceTrackingEntity;
import com.icommerce.iproduct.handler.search.ProductSpecificationBuilder;
import com.icommerce.iproduct.handler.ProductNotFoundException;
import com.icommerce.iproduct.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ProductTrackingDAO priceTrackingDao;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, ProductTrackingDAO priceTrackingDao) {
        this.productDAO = productDAO;
        this.priceTrackingDao = priceTrackingDao;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = productDAO.findAll();
        ModelMapper mapper = new ModelMapper();
        List<Product> list = new ArrayList<>();
        entities.stream().forEach(entity -> {
            list.add(mapper.map(entity, Product.class));
        });
        return list;
    }

    @Override
    public List<Product> search(String search) {

        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<ProductEntity> spec = builder.build();
        ModelMapper mapper = new ModelMapper();
        List<Product> list = new ArrayList<>();
        List<ProductEntity> entities = productDAO.findAll(spec);
        mapper.map(productDAO.findAll(spec), list);
        entities.stream().forEach(entity -> {
            list.add(mapper.map(entity, Product.class));
        });
        return list;
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productEntity = productDAO.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(productEntity, Product.class);
    }

    @Override
    public Product save(ProductRequest request) {
        ModelMapper mapper = new ModelMapper();
        ProductEntity entity = mapper.map(request, ProductEntity.class);
        return mapper.map(productDAO.save(entity), Product.class);
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        ModelMapper mapper = new ModelMapper();
        ProductEntity entity = mapper.map(request, ProductEntity.class);
        entity.setProductId(id);
        handleTrackingChange(id, request);
        return mapper.map(productDAO.save(entity), Product.class);
    }

    private void handleTrackingChange(Long id, ProductRequest request) {
        ModelMapper mapper = new ModelMapper();
        ProductPriceTrackingEntity trackingEntity = new ProductPriceTrackingEntity();
        trackingEntity.setId(0L);
        trackingEntity.setProductId(id);
        trackingEntity.setPrice(request.getPrice());
        trackingEntity.setEffectiveDate(new Date());
        priceTrackingDao.save(trackingEntity);
    }

    @Override
    public void delete(Long id) {
        ProductEntity entity = productDAO.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        entity.setDelete(true);
        productDAO.save(entity);
    }
}
