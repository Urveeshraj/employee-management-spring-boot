package com.urveeshproject.employee.dao;

import com.urveeshproject.employee.dto.EmployeeAgeResultDto;
import com.urveeshproject.employee.entity.Department;
import com.urveeshproject.employee.entity.Employee;
import com.urveeshproject.employee.entity.Payment;
import com.urveeshproject.employee.repository.DepartmentRepository;
import com.urveeshproject.employee.repository.EmployeeRepository;
import com.urveeshproject.employee.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Employee operations
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Department operations
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Payment operations
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Custom query for the SQL problem
    public List<EmployeeAgeResultDto> findEmployeesWithYoungerCount() {
        List<Object[]> results = employeeRepository.findEmployeesWithYoungerCount();
        return results.stream().map(result -> new EmployeeAgeResultDto(
                ((Number) result[0]).longValue(), // empId
                (String) result[1], // firstName
                (String) result[2], // lastName
                (String) result[3], // departmentName
                ((Number) result[4]).longValue() // youngerEmployeesCount
        )).collect(Collectors.toList());
    }
}
