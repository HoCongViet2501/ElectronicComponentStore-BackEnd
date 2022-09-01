package com.be.electroniccomponentstore.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    private Long productId;
    
    private Long orderId;
}
