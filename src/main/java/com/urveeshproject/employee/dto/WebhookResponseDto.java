package com.urveeshproject.employee.dto;

public class WebhookResponseDto {
    private String webhook;
    private String accessToken;

    // Default constructor
    public WebhookResponseDto() {
    }

    // Constructor with parameters
    public WebhookResponseDto(String webhook, String accessToken) {
        this.webhook = webhook;
        this.accessToken = accessToken;
    }

    // Getters and Setters
    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
