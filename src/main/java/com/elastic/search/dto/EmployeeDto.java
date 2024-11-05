package com.elastic.search.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    private String firstName;

    private String lastName;

    private double salary;
}