package com.icommerce.order.controller;

import com.icommerce.iorder.IOrderApplication;
import com.icommerce.iorder.controller.OrderController;
import com.icommerce.iorder.dao.OrderDAO;
import com.icommerce.iorder.model.Order;
import com.icommerce.iorder.model.OrderDetail;
import com.icommerce.iorder.model.OrderRequest;
import com.icommerce.iorder.service.OrderService;
import com.icommerce.iorder.service.impl.OrderServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = IOrderApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Test
    public void contextLoads() {

    }
    @MockBean
    private OrderDAO dao;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init(){
       /* mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();*/
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void store_givenListOrderDetail_returnTrueIfEqual(){
        OrderDAO dao = null;
        OrderService service = new OrderServiceImpl(dao);
        OrderRequest req = new OrderRequest();
        req.setOrderid(getLong(1));
        req.setCustomerId(getLong(1));
        req.setDate(new Date());
        req.setDetails(buildOrderDetails());
        Order order = service.store(req);

        boolean actual = order.getSumPrice() == getDouble(1000);
        assertTrue(actual);
    }

    private Long getLong(int value){
        return Long.valueOf(value);
    }

    private Double getDouble(int value){
        return Double.valueOf(value);
    }

    private Set<OrderDetail> buildOrderDetails(){
        Set<OrderDetail> details = new HashSet<>();
        details.add(buildDetail(1, 200, 1));
        details.add(buildDetail(2, 500, 1));
        details.add(buildDetail(3, 150, 2));
        return details;
    }

    private OrderDetail buildDetail(int value, int price, int quantity){
        OrderDetail detail = new OrderDetail();
        detail.setId(getLong(value));
        detail.setPrice(getDouble(price));
        detail.setQuantity(quantity);
        return detail;
    }
}
