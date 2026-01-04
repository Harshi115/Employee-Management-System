package com.example.employeemanagement;

import com.example.employeemanagement.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeemanagementApplicationTests {

	@Test
	void contextLoads() {
		// Just checks if Spring context loads successfully
		assertTrue(true);
	}

	@Test
	void testSingletonPattern() {
		// Test Singleton pattern
		AppConfig config1 = AppConfig.getInstance();
		AppConfig config2 = AppConfig.getInstance();

		assertSame(config1, config2);
		assertEquals("Employee Management System", config1.getAppName());
	}

}
