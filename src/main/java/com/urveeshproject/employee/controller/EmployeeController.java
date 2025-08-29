package com.urveeshproject.employee.controller;

import com.urveeshproject.employee.dto.EmployeeAgeResultDto;
import com.urveeshproject.employee.entity.Department;
import com.urveeshproject.employee.entity.Employee;
import com.urveeshproject.employee.entity.Payment;
import com.urveeshproject.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Employee endpoints
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setEmpId(id);
        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Department endpoints
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = employeeService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = employeeService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = employeeService.saveDepartment(department);
        return ResponseEntity.ok(savedDepartment);
    }

    // Payment endpoints
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = employeeService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = employeeService.savePayment(payment);
        return ResponseEntity.ok(savedPayment);
    }

    // SQL Problem solution endpoint
    @GetMapping("/younger-count")
    public ResponseEntity<List<EmployeeAgeResultDto>> getEmployeesWithYoungerCount() {
        List<EmployeeAgeResultDto> result = employeeService.getEmployeesWithYoungerCount();
        return ResponseEntity.ok(result);
    }

    // Get final SQL query
    @GetMapping("/final-query")
    public ResponseEntity<String> getFinalSqlQuery() {
        String query = employeeService.getFinalSqlQuery();
        return ResponseEntity.ok(query);
    }
}
