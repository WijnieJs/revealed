package com.example.two.controllers;

import com.example.two.dto.TagDto;
import com.example.two.models.Tag;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.serviceImplents.TagServiceImpl;
import com.example.two.services.serviceInterfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService ) {
        this.tagService = tagService;

    }



    @GetMapping("/tags")
    public ResponseEntity<?> getTags() {
        List<TagDto> tags = tagService.getTags();
        return ResponseEntity.ok(tags);


    }

}
