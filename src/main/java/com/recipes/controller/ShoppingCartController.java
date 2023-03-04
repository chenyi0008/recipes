package com.recipes.controller;

import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Menu;
import com.recipes.entity.ShoppingCart;
import com.recipes.service.MenuService;
import com.recipes.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * shoppingCart
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    MenuService menuService;

    /**
     * 加入购物车
     * @param dishId 菜品的id
     * @param dishFlavor 口味/备注
     * @param number 数量
     * @return
     */
    @GetMapping("add")
    public R<String> add(Long dishId, String dishFlavor, Integer number){

        if(number <= 0)return R.error("菜品数量最低为1");
        Optional<Menu> optionalMenu = menuService.getMenuById(dishId);
        if(!optionalMenu.isPresent())return R.error("不存在此菜品");
        Menu dish = optionalMenu.get();
        Double total = dish.getPrice() * number;

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDishId(dish.getId());
        shoppingCart.setDishFlavor(dishFlavor);
        shoppingCart.setImage(dish.getImage());
        shoppingCart.setUserId(BaseContext.getUserId());
        shoppingCart.setAmount(total);
        return R.msg("添加成功");
    }

    /**
     * 获取购物车数据
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping()
    public R<Page<ShoppingCart>> getAll(Integer page, Integer pageSize){
        Long userId = BaseContext.getUserId();
        Page<ShoppingCart> all = shoppingCartService.getAll(userId, page, pageSize);
        BigDecimal bigDecimal = new BigDecimal(0);
        for (ShoppingCart shoppingCart : all) {
            bigDecimal.add(new BigDecimal(shoppingCart.getAmount()));
        }
        return R.success(all, "total：" + bigDecimal.toString());
    }

    /**
     * 获取菜单全部数据
     */
    @GetMapping("menu")
    public R<Page<Menu>> query(String name, Integer page, Integer pageSize){
        Page<Menu> query = menuService.findByNameLikeAndStatus(name, page, pageSize);
        return R.success(query);
    }





}
