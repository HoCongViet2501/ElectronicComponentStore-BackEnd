package com.be.electroniccomponentstore.dto.response;

import com.be.electroniccomponentstore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String username;
    
    private String password;
    
    private String role;
    
    private String token;
}
