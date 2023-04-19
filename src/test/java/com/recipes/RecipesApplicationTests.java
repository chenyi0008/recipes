package com.recipes;


import com.recipes.mapper.ShoppingCartDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class RecipesApplicationTests {

//    @Autowired
//    DishService menuService;
//
//    @Autowired
//    OrdersDao ordersDao;
//
//    @Autowired
//    OrderDetailDao orderDetailDao;
//    @Test
//    @Transactional
//    void contextLoads() {
//        List<Orders> all = ordersDao.findAll();
//        for (Orders orders : all) {
//            System.out.println(orders);
//            for (OrderDetail orderDetail : orders.detailSet) {
//                System.out.println(orderDetail);
//            }
//        }
//    }
//
    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Test
    @Transactional
    void test1(){
        Integer integer = shoppingCartDao.deleteShoppingCartByIdAndUserId(2, 4);
        System.out.println(integer);
    }

}
