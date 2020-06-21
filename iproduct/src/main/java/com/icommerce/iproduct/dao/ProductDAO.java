package com.icommerce.iproduct.dao;

import com.icommerce.iproduct.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<ProductEntity, Long>
        , JpaSpecificationExecutor<ProductEntity> {

}
