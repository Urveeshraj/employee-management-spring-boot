package com.urveeshproject.employee.service;

import com.urveeshproject.employee.dao.EmployeeDao;
import com.urveeshproject.employee.dto.EmployeeAgeResultDto;
import com.urveeshproject.employee.entity.Department;
import com.urveeshproject.employee.entity.Employee;
import com.urveeshproject.employee.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    // Employee operations
    public List<Employee> getAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }

    // Department operations
    public List<Department> getAllDepartments() {
        return employeeDao.findAllDepartments();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return employeeDao.findDepartmentById(id);
    }

    public Department saveDepartment(Department department) {
        return employeeDao.saveDepartment(department);
    }

    // Payment operations
    public List<Payment> getAllPayments() {
        return employeeDao.findAllPayments();
    }

    public Payment savePayment(Payment payment) {
        return employeeDao.savePayment(payment);
    }

    // SQL Problem solution
    public List<EmployeeAgeResultDto> getEmployeesWithYoungerCount() {
        return employeeDao.findEmployeesWithYoungerCount();
    }

    // Get the final SQL query string for the solution
    public String getFinalSqlQuery() {
        return """
                SELECT e1.EMP_ID,
                       e1.FIRST_NAME,
                       e1.LAST_NAME,
                       d.DEPARTMENT_NAME,
                       COUNT(e2.EMP_ID) as YOUNGER_EMPLOYEES_COUNT
                FROM EMPLOYEE e1
                JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
                LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB
                GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
                ORDER BY e1.EMP_ID DESC
                """;
    }
}
