package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}