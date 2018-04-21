package com.vitkulov.tests.Test_2.dto;


import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.validation.Name;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString(of="name")
public class UserDto {

    private Long id;
    @Name
    private String name;
    private Record record;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public UserDto() {
    }

    public UserDto(String name) {
        this.name = name;
    }
}
