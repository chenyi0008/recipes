package com.recipes.controller;

import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.*;
import com.recipes.service.*;
import com.sun.jndi.cosnaming.IiopUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * shoppingCart
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    DishService dishService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    BoardService boardService;


    /**
     * 点菜
     * @param dishId 菜品的id
     * @param dishFlavor 口味/备注
     * @param number 数量
     * @return
     */
    @GetMapping("add")
    public R<String> add(Integer dishId, String dishFlavor, Integer number){

        if(number <= 0)return R.error("菜品数量最低为1");
        Optional<Dish> optionalMenu = dishService.getDishById(dishId);
        if(!optionalMenu.isPresent())return R.error("不存在此菜品");
        Dish dish = optionalMenu.get();
        Double total = dish.getPrice() * number;

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(null);
        shoppingCart.setName(dish.getName());
        shoppingCart.setNumber(number);
        shoppingCart.setDishId(dish.getId());
        shoppingCart.setDishFlavor(dishFlavor);
        shoppingCart.setImage(dish.getImage());
        shoppingCart.setUserId(BaseContext.getUserId());
        shoppingCart.setStatus(0);
        shoppingCart.setAmount(total);
        shoppingCartService.save(shoppingCart);
        return R.msg("添加成功");
    }

    /**
     * 获取已下单的菜品
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    public R<Page<ShoppingCart>> getAll(@PathVariable Integer page, @PathVariable Integer size){
        Integer userId = BaseContext.getUserId();
        Page<ShoppingCart> all = shoppingCartService.getAll(userId, page, size);
        BigDecimal bigDecimal = new BigDecimal(0);
        for (ShoppingCart shoppingCart : all) {
            bigDecimal.add(new BigDecimal(shoppingCart.getAmount()));
        }
        return R.success(all, "total：" + bigDecimal.toString());
    }

    /**
     * 退菜
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<String> deleteById(@PathVariable Integer id){
        Integer userId = BaseContext.getUserId();
        Optional<ShoppingCart> optional = shoppingCartService.getById(id);
        if(!optional.isPresent())return R.error("不存在此记录");
        if(optional.get().getStatus() == 1)return R.error("菜品已上，退菜失败");

        Integer integer = shoppingCartService.deleteById(id, userId);
        if(integer == 1)return R.msg("退菜成功");
        else return R.error("退菜失败");
    }

    /**
     * 下单结算
    */
    @Transactional
    @GetMapping()
    public R<String> settleAccounts(){
        Integer userId = BaseContext.getUserId();
        Page<ShoppingCart> page = shoppingCartService.getAll(userId, 1, Integer.MAX_VALUE);
        Optional<Board> optionalBoard = boardService.findByUserId(userId);
        if(!optionalBoard.isPresent())return R.error("请先扫描餐桌的二维码");
        shoppingCartService.deleteAll(userId);
        Double sum = 0.0;
        Orders orders = new Orders();
        orders.setTableId(optionalBoard.get().getStoreId());

        for (ShoppingCart cart : page) {
            System.out.println(cart);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(cart.getName());
            orderDetail.setImage(cart.getImage());
            orderDetail.setDishFlavor(cart.getDishFlavor());
            orderDetail.setNumber(cart.getNumber());
            orderDetail.setAmount(cart.getAmount());
            orderDetail.setDishId(cart.getDishId());
            sum += cart.getAmount();
            orderDetail.setOrders(orders);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        orders.setOrderTime(formattedDateTime);
        orders.setAmount(sum);
        orders.setUserId(userId);
        orders.setUserName(BaseContext.getUsername());
        ordersService.save(orders);
        return R.msg("订单生成成功,共计：" + sum + "元");
    }

    /**
     * 上菜
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<String> serve(@PathVariable Integer id){
        Optional<ShoppingCart> op = shoppingCartService.getById(id);
        if(!op.isPresent())return R.error("不存在此记录");
        ShoppingCart shoppingCart = op.get();
        shoppingCart.setStatus(1);
        shoppingCartService.update(shoppingCart);
        return R.msg("上菜成功");
    }


}