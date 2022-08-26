package com.example.two.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/auth")
@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') ")
public class AdminController {

    @GetMapping("/all")
    public String allAccess() {
        return
                "Admin mode";
    }



    @GetMapping("/mod")
    public String moderatorAccess() {
        return "Moderator Board.";
    }


    // ROUTES ONLY FOR ADMIN AUTHORITY

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
