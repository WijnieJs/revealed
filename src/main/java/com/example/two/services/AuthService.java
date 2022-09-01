package com.example.two.services;

import com.example.two.dto.ResponseDto;
import com.example.two.security.request.LoginRequest;
import com.example.two.security.request.SignupRequest;
import com.example.two.security.response.JwtResponse;
import org.springframework.http.ResponseEntity;




//FACADE
// Using Dependency injection to acces the auth anywere ,
// the facade allows for the authObj , while it can hide the static state.

public interface AuthService {
    ResponseDto register(SignupRequest signUpRequest);
    ResponseEntity<JwtResponse> getAuthentication(LoginRequest loginRequest);
}
