package com.be.electroniccomponentstore.service.impl;

import com.be.electroniccomponentstore.dto.request.AccountRequest;
import com.be.electroniccomponentstore.dto.response.AccountResponse;
import com.be.electroniccomponentstore.exceptions.JwtAuthenticationException;
import com.be.electroniccomponentstore.repository.AccountRepository;
import com.be.electroniccomponentstore.security.jwt.JwtProvider;
import com.be.electroniccomponentstore.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private final AccountRepository accountRepository;
    
    private final AuthenticationManager authenticationManager;
    
    private final JwtProvider jwtProvider;
    
    @Autowired
    public AuthenticationServiceImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    
    @Override
    public AccountResponse login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String token = jwtProvider.createToken(username, String.valueOf(role));
            return new AccountResponse(username, password, role, token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new JwtAuthenticationException("username or password is incorrect!");
        }
    }
    
    @Override
    public AccountResponse register(AccountRequest accountRequest) {
        
        return null;
    }
    
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }
}
