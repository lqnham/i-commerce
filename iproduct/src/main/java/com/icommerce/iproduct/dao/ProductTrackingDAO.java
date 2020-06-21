package com.icommerce.iproduct.dao;

import com.icommerce.iproduct.entity.ProductPriceTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTrackingDAO extends JpaSpecificationExecutor<ProductPriceTrackingEntity>,
        JpaRepository<ProductPriceTrackingEntity, Long> {
    //Repository of Product price tracking
}
