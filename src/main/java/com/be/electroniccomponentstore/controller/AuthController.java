package com.be.electroniccomponentstore.controller;

import com.be.electroniccomponentstore.dto.request.LoginRequest;
import com.be.electroniccomponentstore.dto.response.AccountResponse;
import com.be.electroniccomponentstore.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/login")
    @Operation(summary = "login for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login successfully!"),
            @ApiResponse(responseCode = "403", description = "incorrect username or password")
    })
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest loginRequest) {
        String password = loginRequest.getPassword();
        AccountResponse loginResponse = this.authenticationService.login(loginRequest.getUsername(), password);
        loginResponse.setPassword(passwordEncoder.encode(password));
        return ResponseEntity.ok().body(loginResponse);
    }
}
