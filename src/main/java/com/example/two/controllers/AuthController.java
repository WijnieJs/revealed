package com.example.two.controllers;

import com.example.two.dto.ResponseDto;
import com.example.two.repository.UserRepository;
import com.example.two.security.request.LoginRequest;
import com.example.two.security.request.SignupRequest;
import com.example.two.security.response.JwtResponse;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.AuthServiceImpl;
import com.example.two.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthServiceImpl authServiceImpl;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Caaaaaontent.";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signUpRequest, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

      ResponseDto user = authServiceImpl.signUp(signUpRequest);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            return authServiceImpl.authenticateUser(loginRequest);
    }


}