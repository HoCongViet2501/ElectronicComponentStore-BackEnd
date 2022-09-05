package com.be.electroniccomponentstore.service;

import com.be.electroniccomponentstore.dto.request.AccountRequest;
import com.be.electroniccomponentstore.dto.response.AccountResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    AccountResponse login(String username, String password);
    
    AccountResponse register(AccountRequest accountRequest);
    
    void logout(HttpServletRequest request, HttpServletResponse response);
    
    
}
