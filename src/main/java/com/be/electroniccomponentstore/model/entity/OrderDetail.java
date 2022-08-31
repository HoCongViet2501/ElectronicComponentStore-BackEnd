package com.be.electroniccomponentstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "order_detail")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId orderDetailId;
    
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id") 
    private Order order;
    
    private int quantity;
    
    private String payment;
}
