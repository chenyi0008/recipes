package com.recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "image")
    String image;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "dish_id")
    Long dishId;

    @Column(name = "dish_flavor")
    String dishFlavor;

    @Column(name = "number")
    Integer number;

    @Column(name = "amount")
    Double amount;

    @Column(name = "create_time")
    String createTime;
}
