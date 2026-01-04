package com.example.employeemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Point 5: Mock 3rd party salary API using Spring's MockRestServiceServer
 * This test simulates calling an external salary service
 */
public class SalaryWireMockTest {

    @Test
    void shouldReturnSalaryFromMockedThirdPartyAPI() {
        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Create MockRestServiceServer (mocks 3rd party API)
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        // Setup mock response for salary endpoint
        mockServer.expect(requestTo("http://localhost:8081/salary/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"salary\":50000}", MediaType.APPLICATION_JSON));

        // Make request to mocked endpoint
        String response = restTemplate.getForObject(
                "http://localhost:8081/salary/1",
                String.class
        );

        // Verify response from mocked API
        assertEquals("{\"salary\":50000}", response);

        // Verify all expectations met
        mockServer.verify();
    }
}