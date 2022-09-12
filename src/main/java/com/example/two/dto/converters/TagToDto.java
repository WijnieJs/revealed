package com.example.two.dto.converters;


import com.example.two.models.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagToDto {
    public static   String of(Tag tag) {

        return tag.getName();

    }
}
