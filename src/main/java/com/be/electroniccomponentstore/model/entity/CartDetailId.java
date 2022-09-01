package com.be.electroniccomponentstore.model.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartDetailId implements Serializable {
    private Long cartId;
    
    private Long productId;
    
}
