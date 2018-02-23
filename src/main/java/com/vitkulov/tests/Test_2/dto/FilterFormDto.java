package com.vitkulov.tests.Test_2.dto;

import lombok.Data;

@Data
public class FilterFormDto {

    private Long userId;
    private String userName;
    private String startDate;
    private String endDate;
}
