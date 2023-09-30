package com.example.SpringbootEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "Order_quantity")
public class OrderQuantities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
//    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_order_quantity", foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE"))
//    private Product product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public Order getOrder() {
//        return order;
//    }
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//    public Integer getQuantity() {
//        return quantity;
//    }
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
