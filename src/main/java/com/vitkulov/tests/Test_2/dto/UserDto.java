package com.vitkulov.tests.Test_2.dto;


import com.vitkulov.tests.Test_2.validation.Name;

public class UserDto {

    private Long id;

    @Name
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
