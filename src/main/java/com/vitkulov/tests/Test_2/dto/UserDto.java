package com.vitkulov.tests.Test_2.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    public UserDto() {
    }

    public UserDto(String name) {
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

    @Override
    public String toString() {
        return "User(Name: " + this.name + ")";
    }
}
