package com.example.two.dto;

import javax.validation.constraints.NotNull;

public class TagDto {



    private Long  id;
    private @NotNull String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
