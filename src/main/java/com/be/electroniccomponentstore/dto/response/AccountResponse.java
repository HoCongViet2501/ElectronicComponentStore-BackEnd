package com.be.electroniccomponentstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String username;
    
    private String password;
    
    private String role;
    
    private String token;
}
