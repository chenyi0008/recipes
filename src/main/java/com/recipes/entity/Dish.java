package com.recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "dish")
public class Dish {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @Column
    Double price;

    @Column
    String image;

    @Column
    String description;

    @Column
    Integer status;

    @Column(name = "category_id")
    Integer categoryId;



//    @OneToOne(fetch = FetchType.EAGER)
//    @JsonBackReference
//    OrderDetail orderDetail;
}
