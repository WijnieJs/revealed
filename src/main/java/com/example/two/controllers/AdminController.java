package com.example.two.controllers;

import com.example.two.dto.ProductDto;
import com.example.two.utils.Helper;
import com.example.two.utils.MapValidationErrorService;
import com.example.two.security.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/auth")
@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') ")
public class AdminController {


    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/mod")
    public String moderatorAccess() {
        return "Moderator Board.";
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;
//            if(Helper.notNull(mapValidationErrorService.MapValidationService(result))) {
//               return mapValidationErrorService.MapValidationService(result);
//            }
        return new ResponseEntity<MessageResponse>(new MessageResponse("User Registered Successfully"), HttpStatus.CREATED);
    }
    // ROUTES ONLY FOR ADMIN AUTHORITY

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
