package com.be.electroniccomponentstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "cart_detail")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {
    @EmbeddedId
    private CartDetailId cartDetailId;
    
    @MapsId("cartId")
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private int quantity;
}
