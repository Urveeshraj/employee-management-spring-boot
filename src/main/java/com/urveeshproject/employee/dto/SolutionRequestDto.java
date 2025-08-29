package com.urveeshproject.employee.dto;

public class SolutionRequestDto {
    private String finalQuery;

    // Default constructor
    public SolutionRequestDto() {
    }

    // Constructor with parameters
    public SolutionRequestDto(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    // Getters and Setters
    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
}
