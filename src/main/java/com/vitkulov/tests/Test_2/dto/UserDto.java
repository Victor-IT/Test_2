package com.vitkulov.tests.Test_2.dto;


import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.validation.Name;

public class UserDto {

    private Long id;

    @Name
    private String name;

    private Record record;

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

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "User(Name: " + this.name + ")";
    }
}
