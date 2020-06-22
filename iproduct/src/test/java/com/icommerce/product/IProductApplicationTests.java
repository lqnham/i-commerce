package com.icommerce.product;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.icommerce.iproduct.service.ProductService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class IProductApplicationTests {

    @TestConfiguration
    public static class TodoServiceTest2Configuration{

       /* @Bean
        ProductService productService(){
            return new ProductService();
        }*/
    }

    @Autowired
    private ProductService todoService;

    @Test
    public void abc_sr_ee() {

        String params = "search?search=price<2000";
    }
}
