package com.icommerce.iproduct.configuration;

import com.icommerce.iproduct.dao.ProductDAO;
import com.icommerce.iproduct.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitialize implements ApplicationRunner {
    private ProductDAO dao;

    @Autowired
    public DataInitialize(ProductDAO dao) {
        this.dao = dao;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = dao.count();
        if (count == 0) {
            for (int i = 1; i < 15; i++) {
                ProductEntity product = new ProductEntity();
                product.setName("Macbook 16 inc" + i);
                product.setColor("Grey" + i);
                product.setPrice(Double.valueOf(2000) + i);
                product.setBranchName("VN" + i);
                product.setColor("Yellow" + i);
                product.setQuantity(i);
                product.setDelete(false);
                dao.save(product);
            }

        }
    }
}
