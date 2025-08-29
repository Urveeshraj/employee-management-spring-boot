package com.urveeshproject.employee.dto;

public class EmployeeAgeResultDto {
    private Long empId;
    private String firstName;
    private String lastName;
    private String departmentName;
    private Long youngerEmployeesCount;

    // Default constructor
    public EmployeeAgeResultDto() {
    }

    // Constructor with parameters
    public EmployeeAgeResultDto(Long empId, String firstName, String lastName, String departmentName,
            Long youngerEmployeesCount) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
        this.youngerEmployeesCount = youngerEmployeesCount;
    }

    // Getters and Setters
    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getYoungerEmployeesCount() {
        return youngerEmployeesCount;
    }

    public void setYoungerEmployeesCount(Long youngerEmployeesCount) {
        this.youngerEmployeesCount = youngerEmployeesCount;
    }
}
