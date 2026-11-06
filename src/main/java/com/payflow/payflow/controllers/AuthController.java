package com.payflow.payflow.controllers;

import com.payflow.payflow.dtos.AuthRequestDto;
import com.payflow.payflow.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDto requestDto){
        String registered = authService.register(requestDto.getUsername(), requestDto.getPassword());
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto request){
        String loggedIn = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(loggedIn);
    }

}
