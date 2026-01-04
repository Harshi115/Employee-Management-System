package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new Employee();
        testEmployee.setId(1L);
        testEmployee.setName("Test Employee");
        testEmployee.setEmail("test@example.com");
        testEmployee.setDepartment("IT");
        testEmployee.setSalary(50000.0);
    }

    @Test
    void testCreateEmployee() {
        // Given
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // When
        Employee result = employeeService.createEmployee(testEmployee);

        // Then
        assertNotNull(result);
        assertEquals("Test Employee", result.getName());
        assertEquals("test@example.com", result.getEmail());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeById_Success() {
        // Given
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        // When
        Employee result = employeeService.getEmployeeById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Employee", result.getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        // Given
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(999L);
        });

        assertTrue(exception.getMessage().contains("not found"));
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    void testGetAllEmployees() {
        // Given
        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Employee 2");
        emp2.setEmail("emp2@example.com");
        emp2.setDepartment("HR");
        emp2.setSalary(60000.0);

        List<Employee> employees = Arrays.asList(testEmployee, emp2);
        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        List<Employee> result = employeeService.getAllEmployees();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test Employee", result.get(0).getName());
        assertEquals("Employee 2", result.get(1).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee_Success() {
        // Given
        Employee updateData = new Employee();
        updateData.setName("Updated Name");
        updateData.setEmail("updated@example.com");
        updateData.setDepartment("Finance");
        updateData.setSalary(70000.0);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // When
        Employee result = employeeService.updateEmployee(1L, updateData);

        // Then
        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        // Given
        doNothing().when(employeeRepository).deleteById(1L);

        // When
        employeeService.deleteEmployee(1L);

        // Then
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}