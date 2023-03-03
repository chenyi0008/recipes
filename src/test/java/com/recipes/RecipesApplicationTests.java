package com.recipes;

import com.recipes.entity.Menu;
import com.recipes.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class RecipesApplicationTests {

    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {
        Page<Menu> query = menuService.query("辣", 1, 10);
//        System.out.println(query);
        for (Menu menu : query) {
            System.out.println(menu);
        }
    }

}
