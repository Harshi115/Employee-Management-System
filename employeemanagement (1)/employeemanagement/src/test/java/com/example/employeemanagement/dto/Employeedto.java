package com.example.employeemanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employeedto {

    private Long id;
    private String name;
    private String email;
    private Double salary;
}