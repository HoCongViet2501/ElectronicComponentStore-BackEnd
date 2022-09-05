package com.be.electroniccomponentstore.controller;

import com.be.electroniccomponentstore.dto.request.AccountRequest;
import com.be.electroniccomponentstore.dto.request.LoginRequest;
import com.be.electroniccomponentstore.dto.response.AccountResponse;
import com.be.electroniccomponentstore.dto.response.LoginResponse;
import com.be.electroniccomponentstore.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    
    
    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("/login")
    @Operation(summary = "login for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "login successfully!"),
            @ApiResponse(responseCode = "403", description = "incorrect username or password")
    })
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest loginRequest) {
        String password = loginRequest.getPassword();
        LoginResponse loginResponse = this.authenticationService.login(loginRequest.getUsername(), password);
        return ResponseEntity.accepted().body(loginResponse);
    }
    
    @Operation(summary = "register for new user")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create New User successfully")
    })
    public ResponseEntity<Object> register(@Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = this.authenticationService.register(accountRequest);
        return ResponseEntity.ok().body(accountResponse);
    }
    
    @Operation(summary = "logout for user")
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.authenticationService.logout(httpServletRequest, httpServletResponse);
        return ResponseEntity.ok("Logout.user.successfully!");
    }
}
