package com.example.two.controllers;

import com.example.two.dto.ResponseDto;
import com.example.two.repository.UserRepository;
import com.example.two.security.request.LoginRequest;
import com.example.two.security.request.SignupRequest;
import com.example.two.security.response.JwtResponse;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AuthController {



    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;


    @PostMapping("/all")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signUpRequest) {

      ResponseDto user = authService.signUp(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            return authService.authenticateUser(loginRequest);
    }


}