package com.example.two.dto;

import javax.validation.constraints.NotNull;

public class TagDto {



    private Long id;
    private @NotNull String name;

    public TagDto() {
    }

    public TagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
