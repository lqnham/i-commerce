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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Product> search(String search, String columnToSort) {
        //Possible to enhance sortASC || sort DESC
        Pageable paging =  PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, columnToSort));
        Specification<ProductEntity> spec = processParam(search);
        ModelMapper mapper = new ModelMapper();

        return productDAO.findAll(spec, paging)
                .map(entity -> mapper.map(entity, Product.class));
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
        handleTrackingChange(id, request.getPrice());
        return mapper.map(productDAO.save(entity), Product.class);
    }

    /**
     * Store the price change
     * @param id
     * @param price
     */
    private void handleTrackingChange(Long id, Double price) {
        ModelMapper mapper = new ModelMapper();
        ProductPriceTrackingEntity trackingEntity = new ProductPriceTrackingEntity();
        trackingEntity.setProductId(id);
        trackingEntity.setPrice(price);
        trackingEntity.setEffectiveDate(new Date());
        priceTrackingDao.save(trackingEntity);
    }

    @Override
    public void delete(Long id) {
        ProductEntity entity = productDAO.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        entity.setDelete(true);
        productDAO.save(entity);
    }

    /**
     * Given param and process then convert to Specification
     * @param search
     * @return
     */
    private Specification<ProductEntity> processParam(String search) {
        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }
}
