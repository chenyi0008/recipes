package com.recipes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "form_detail")
public class FormDetail implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @Column
    String image;

    @Column(name = "dish_flavor")
    String dishFlavor;

    @Column
    Integer number;

    @Column
    Double amount;

    @Column(name = "dish_id")
    Integer dishId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orders;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "dish_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Dish dish;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", dishFlavor='" + dishFlavor + '\'' +
                ", number=" + number +
                ", amount=" + amount +
                ", dishId=" + dishId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormDetail that = (FormDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(image, that.image) && Objects.equals(dishFlavor, that.dishFlavor) && Objects.equals(number, that.number) && Objects.equals(amount, that.amount) && Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, dishFlavor, number, amount, dishId);
    }
}
