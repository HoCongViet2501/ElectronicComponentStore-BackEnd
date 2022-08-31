package com.be.electroniccomponentstore.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class OrderDetailId implements Serializable {
    private Long productId;
    
    private Long orderId;
}
