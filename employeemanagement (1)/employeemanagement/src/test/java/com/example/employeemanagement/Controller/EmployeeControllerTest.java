package com.example.employeemanagement.Controller;

import static org.junit.jupiter.api.Assertions.*;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // Rollback after each test
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        // Clean database before each test
        employeeRepository.deleteAll();

        // Create test employee
        testEmployee = new Employee();
        testEmployee.setName("John Doe");
        testEmployee.setEmail("john@example.com");
        testEmployee.setDepartment("IT");
        testEmployee.setSalary(50000.0);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        // Given - Save employees to database
        employeeRepository.save(testEmployee);

        Employee emp2 = new Employee();
        emp2.setName("Jane Smith");
        emp2.setEmail("jane@example.com");
        emp2.setDepartment("HR");
        emp2.setSalary(60000.0);
        employeeRepository.save(emp2);

        // When & Then
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        // Given - Save employee
        Employee saved = employeeRepository.save(testEmployee);

        // When & Then
        mockMvc.perform(get("/api/employees/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.department").value("IT"));
    }

    @Test
    void testAddEmployee() throws Exception {
        // Given - New employee JSON
        Employee newEmployee = new Employee();
        newEmployee.setName("Test User");
        newEmployee.setEmail("test@example.com");
        newEmployee.setDepartment("Finance");
        newEmployee.setSalary(55000.0);

        // When & Then
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        // Given - Save employee first
        Employee saved = employeeRepository.save(testEmployee);

        // Create update data
        Employee updateData = new Employee();
        updateData.setName("John Updated");
        updateData.setEmail("john.updated@example.com");
        updateData.setDepartment("Finance");
        updateData.setSalary(70000.0);

        // When & Then
        mockMvc.perform(put("/api/employees/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"))
                .andExpect(jsonPath("$.salary").value(70000.0));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        // Given - Save employee first in database
        Employee saved = employeeRepository.save(testEmployee);
        Long employeeId = saved.getId();

        // When - Delete the employee
        mockMvc.perform(delete("/api/employees/" + employeeId))
                .andExpect(status().isNoContent());

        // Then - Verify employee is deleted from database
        assertFalse(employeeRepository.findById(employeeId).isPresent());
    }
}