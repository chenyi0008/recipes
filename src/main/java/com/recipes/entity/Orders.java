package com.recipes.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Orders  implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_time")
    private String orderTime;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "board_id")
    private Integer boardId;

    @Column(name = "store_id")
    private Integer storeId;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderTime='" + orderTime + '\'' +
                ", amount=" + amount +
                ", userName='" + userName + '\'' +
//                ", detailSet=" + detailSet +
                '}';
    }

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference
    public Set<OrderDetail> detailSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) && Objects.equals(userId, orders.userId) && Objects.equals(orderTime, orders.orderTime) && Objects.equals(amount, orders.amount)  && Objects.equals(userName, orders.userName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderTime, amount,   userName);
    }
}

