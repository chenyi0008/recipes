package com.recipes.controller;

import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.*;
import com.recipes.service.AddressBookService;
import com.recipes.service.MenuService;
import com.recipes.service.OrdersService;
import com.recipes.service.ShoppingCartService;
import com.sun.jndi.cosnaming.IiopUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    MenuService menuService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    AddressBookService addressBookService;

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
        shoppingCart.setId(null);
        shoppingCart.setName(dish.getName());
        shoppingCart.setNumber(number);
        shoppingCart.setDishId(dish.getId());
        shoppingCart.setDishFlavor(dishFlavor);
        shoppingCart.setImage(dish.getImage());
        shoppingCart.setUserId(BaseContext.getUserId());
        shoppingCart.setAmount(total);
        shoppingCartService.save(shoppingCart);
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
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteById(Long id){
        Long userId = BaseContext.getUserId();
        Integer integer = shoppingCartService.deleteById(id, userId);
        if(integer == 1)return R.msg("删除成功");
        else return R.error("删除失败");
    }


    /**
     * 获取菜单全部数据
     */
    @GetMapping("menu")
    public R<Page<Menu>> query(String name, Integer page, Integer pageSize){
        Page<Menu> query = menuService.findByNameLikeAndStatus(name, page, pageSize);
        return R.success(query);
    }

    /**
     * 下单结算
     */
    @Transactional
    @GetMapping("settleAccounts")
    public R<String> settleAccounts(Long addressId, String remark, String phone, String consignee){
        Long userId = BaseContext.getUserId();
        Page<ShoppingCart> page = shoppingCartService.getAll(userId, 1, Integer.MAX_VALUE);
//        shoppingCartService.deleteAll(userId);
        Double sum = 0.0;
        Orders orders = new Orders();
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
            orders.getDetailSet().add(orderDetail);
        }

        AddressBook addressBook = addressBookService.getById(userId, addressId);

        if(addressBook == null)return R.error("地址id有误");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        orders.setOrderTime(formattedDateTime);
        orders.setAmount(sum);
        orders.setAddress(addressBook.getAddress());
        orders.setUserId(userId);
        orders.setPhone(phone);
        orders.setRemark(remark);
        orders.setConsignee(consignee);
        orders.setUserName(BaseContext.getUsername());
        ordersService.save(orders);
        return R.msg("结算成功,共支付" + sum + "元");
    }

}
