package com.recipes.controller;

import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.*;
import com.recipes.service.*;
import com.recipes.util.MyList;
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
     * 加入购物车
     * @param dishId 菜品的id
     * @param dishFlavor 口味/备注
     * @param number 数量
     * @return
     */
    @GetMapping("add")
    public R<String> add(Integer dishId, String dishFlavor, Integer number, Integer boardId){
        Integer userId = BaseContext.getUserId();
        if(number <= 0)return R.error("菜品数量最低为1");

        Optional<Dish> optionalMenu = dishService.getDishById(dishId);

        if(!optionalMenu.isPresent())return R.error("不存在此菜品");
        Board board;
        if(boardId.equals(-1)){
            board = new Board();
            board.setId(-1);
            Integer storeId = optionalMenu.get().getCategory().getStoreId();
            board.setStoreId(storeId);
        }
        Optional<Board> optionalBoard = boardService.queryById(boardId);

        if(!optionalBoard.isPresent()){
            return R.error("不存在此餐桌");
        }else{
            board = optionalBoard.get();
        }


        Dish dish = optionalMenu.get();
        Double total = dish.getPrice() * number;

        //如果能在数据库查到对应的记录 直接增加
        ShoppingCart sc = shoppingCartService.getShoppingCart(userId, dishId, dishFlavor);
        if(sc != null){
            int num = sc.getNumber() + number;
            sc.setNumber(num);
            sc.setAmount(dish.getPrice() * num);
            shoppingCartService.update(sc);
            return R.msg("更新成功");
        }

        //查不到记录 添加一条记录
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBoardId(board.getId());
        shoppingCart.setStoreId(board.getStoreId());

        shoppingCart.setId(null);
        shoppingCart.setName(dish.getName());
        shoppingCart.setNumber(number);
        shoppingCart.setDishId(dish.getId());
        shoppingCart.setDishFlavor(dishFlavor);
        shoppingCart.setImage(dish.getImage());
        shoppingCart.setUserId(BaseContext.getUserId());
        shoppingCart.setStatus(-1);
        shoppingCart.setAmount(total);
        shoppingCartService.save(shoppingCart);
        return R.msg("添加成功");
    }

    /**
     * 获取购物车菜品
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    public R<Page<ShoppingCart>> getAll(@PathVariable Integer page, @PathVariable Integer size){
        Integer userId = BaseContext.getUserId();
        Page<ShoppingCart> all = shoppingCartService.findAllByStatusAndUserId(-1, page, size, userId);
        BigDecimal bigDecimal = new BigDecimal(0);
        for (ShoppingCart shoppingCart : all) {
            bigDecimal.add(new BigDecimal(shoppingCart.getAmount()));
        }
        return R.success(all, "total：" + bigDecimal.toString());
    }

    /**
     * 获取未上菜列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}/0")
    public R<Page<ShoppingCart>> getByStatus0(@PathVariable Integer page, @PathVariable Integer size){
        Integer userId = BaseContext.getUserId();
        Page<ShoppingCart> all = shoppingCartService.findAllByStatusAndUserId(0, page, size, userId);
        return R.success(all);
    }
    /**
     * 获取已上菜列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}/1")
    public R<Page<ShoppingCart>> getByStatus1(@PathVariable Integer page, @PathVariable Integer size){
        Integer userId = BaseContext.getUserId();
        Page<ShoppingCart> all = shoppingCartService.findAllByStatusAndUserId(1, page, size, userId);
        return R.success(all);
    }


    /**
     * 退菜/删除购物车菜品
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
        if(integer == 1)return R.msg("删除成功");
        else return R.error("删除失败");
    }

    /**
     * 下单
     * @return
     */
    @PostMapping()
    public R<String> placeOrder(@RequestBody MyList<Integer> myList){
        Integer userId = BaseContext.getUserId();

        List<Integer> list = myList.getList();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        Page<ShoppingCart> page = shoppingCartService.findAllByStatusAndUserIdAndIdIn(-1, 1, Integer.MAX_VALUE, userId, list);

        List<ShoppingCart> content = page.getContent();
        for (int i = 0; i < content.size(); i++) {

            ShoppingCart shoppingCart = content.get(i);
            shoppingCart.setStatus(0);
            System.out.println(shoppingCart.getName());
        }
        shoppingCartService.saveList(content);
        return R.msg("加入成功");
    }

    /**
     * 买单
    */
    @Transactional
    @GetMapping("")
    public R<String> settleAccounts(){
        Integer userId = BaseContext.getUserId();
        Page<ShoppingCart> page = shoppingCartService.getAll(userId, 1, Integer.MAX_VALUE);
        if(page.getSize() == 0)return R.error("未下单菜品，无法买单");


        shoppingCartService.deleteAllByUserIdAndStatusNot(userId, -1);

        Map<Integer, Orders> map = new TreeMap<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        for (ShoppingCart shoppingCart : page.getContent()) {
            Integer boardId = shoppingCart.getBoardId();
            Integer storeId = shoppingCart.getStoreId();
            if(!map.containsKey(boardId)){
                Orders orders = new Orders();
                orders.setBoardId(boardId);
                orders.setStoreId(storeId);
                orders.setUserId(userId);
                orders.setUserName(BaseContext.getUsername());
                orders.setOrderTime(formattedDateTime);
                orders.setDetailSet(new HashSet<OrderDetail>());
                orders.setAmount(0.0);
                map.put(boardId, orders);
            }

            OrderDetail orderDetail = new OrderDetail();
            Orders orders = map.get(boardId);
            orderDetail.setName(shoppingCart.getName());
            orderDetail.setImage(shoppingCart.getImage());
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            orderDetail.setNumber(shoppingCart.getNumber());
            orderDetail.setAmount(shoppingCart.getAmount());
            orderDetail.setDishId(shoppingCart.getDishId());
            orderDetail.setOrders(orders);
            orders.setAmount(orders.getAmount() + shoppingCart.getAmount());
            orders.getDetailSet().add(orderDetail);
        }
        Double sum = 0.0;
        List<Orders> res = new ArrayList<>();
        for (Orders value : map.values()) {
            sum += value.getAmount();
            res.add(value);
        }
        ordersService.saveAll(res);
        return R.msg("订单生成成功,共计：" + sum + "元");
    }




}
