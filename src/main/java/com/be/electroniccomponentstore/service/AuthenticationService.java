package com.be.electroniccomponentstore.service;

import com.be.electroniccomponentstore.dto.request.LoginRequest;
import com.be.electroniccomponentstore.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(String username,String password);
}
