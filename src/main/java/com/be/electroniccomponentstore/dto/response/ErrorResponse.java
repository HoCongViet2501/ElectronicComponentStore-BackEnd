package com.be.electroniccomponentstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Date timeStamp;
    
    private String message;
    
    private String error;
    
}
