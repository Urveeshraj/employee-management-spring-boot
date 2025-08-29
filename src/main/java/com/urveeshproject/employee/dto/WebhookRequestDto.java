package com.urveeshproject.employee.dto;

public class WebhookRequestDto {
    private String name;
    private String regNo;
    private String email;

    // Default constructor
    public WebhookRequestDto() {
    }

    // Constructor with parameters
    public WebhookRequestDto(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
