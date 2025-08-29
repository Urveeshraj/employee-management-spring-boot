package com.urveeshproject.employee.service;

import com.urveeshproject.employee.dto.SolutionRequestDto;
import com.urveeshproject.employee.dto.WebhookRequestDto;
import com.urveeshproject.employee.dto.WebhookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Autowired
    private EmployeeService employeeService;

    private final RestTemplate restTemplate;
    private final String WEBHOOK_GENERATE_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private final String WEBHOOK_SUBMIT_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public WebhookService() {
        this.restTemplate = new RestTemplate();
    }

    public void processWebhookFlow() {
        try {
            // Step 1: Generate webhook
            WebhookResponseDto webhookResponse = generateWebhook();

            if (webhookResponse != null && webhookResponse.getWebhook() != null
                    && webhookResponse.getAccessToken() != null) {
                // Step 2: Get the SQL solution
                String finalQuery = employeeService.getFinalSqlQuery();

                // Step 3: Submit the solution
                submitSolution(webhookResponse.getAccessToken(), finalQuery);

                System.out.println("Webhook flow completed successfully!");
            } else {
                System.err.println("Failed to generate webhook - received null response");
            }

        } catch (Exception e) {
            System.err.println("Error in webhook flow: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private WebhookResponseDto generateWebhook() {
        try {
            // Create request body
            WebhookRequestDto request = new WebhookRequestDto("John Doe", "REG12347", "john@example.com");

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookRequestDto> entity = new HttpEntity<>(request, headers);

            // Make POST request
            ResponseEntity<WebhookResponseDto> response = restTemplate.exchange(
                    WEBHOOK_GENERATE_URL,
                    HttpMethod.POST,
                    entity,
                    WebhookResponseDto.class);

            System.out.println("Webhook generation response: " + response.getStatusCode());
            return response.getBody();

        } catch (Exception e) {
            System.err.println("Error generating webhook: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void submitSolution(String accessToken, String finalQuery) {
        try {
            // Create request body
            SolutionRequestDto request = new SolutionRequestDto(finalQuery);

            // Set headers with JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<SolutionRequestDto> entity = new HttpEntity<>(request, headers);

            // Make POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    WEBHOOK_SUBMIT_URL,
                    HttpMethod.POST,
                    entity,
                    String.class);

            System.out.println("Solution submission response: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error submitting solution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
