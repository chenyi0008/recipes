package com.recipes;

import com.recipes.entity.Menu;
import com.recipes.entity.OrderDetail;
import com.recipes.entity.Orders;
import com.recipes.mapper.OrderDetailDao;
import com.recipes.mapper.OrdersDao;
import com.recipes.mapper.ShoppingCartDao;
import com.recipes.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class RecipesApplicationTests {

    @Autowired
    MenuService menuService;

    @Autowired
    OrdersDao ordersDao;

    @Autowired
    OrderDetailDao orderDetailDao;
    @Test
    @Transactional
    void contextLoads() {
        List<Orders> all = ordersDao.findAll();
        for (Orders orders : all) {
            System.out.println(orders);
            for (OrderDetail orderDetail : orders.detailSet) {
                System.out.println(orderDetail);
            }
        }
    }

    @Test
    void test1(){

    }

}
