package com.recipes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "menu")
public class Menu {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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
    Long categoryId;



//    @OneToOne(fetch = FetchType.EAGER)
//    @JsonBackReference
//    OrderDetail orderDetail;
}
