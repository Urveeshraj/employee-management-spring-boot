package com.urveeshproject.employee.service;

import com.urveeshproject.employee.entity.Department;
import com.urveeshproject.employee.entity.Employee;
import com.urveeshproject.employee.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        if (employeeService.getAllDepartments().isEmpty()) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create departments
        Department hr = new Department("HR");
        Department finance = new Department("Finance");
        Department engineering = new Department("Engineering");
        Department sales = new Department("Sales");
        Department marketing = new Department("Marketing");
        Department it = new Department("IT");

        hr = employeeService.saveDepartment(hr);
        finance = employeeService.saveDepartment(finance);
        engineering = employeeService.saveDepartment(engineering);
        sales = employeeService.saveDepartment(sales);
        marketing = employeeService.saveDepartment(marketing);
        it = employeeService.saveDepartment(it);

        // Create employees
        Employee emp1 = new Employee("John", "Williams", LocalDate.of(1980, 5, 15), "Male", engineering);
        Employee emp2 = new Employee("Sarah", "Johnson", LocalDate.of(1990, 7, 20), "Female", finance);
        Employee emp3 = new Employee("Michael", "Smith", LocalDate.of(1985, 2, 10), "Male", engineering);
        Employee emp4 = new Employee("Emily", "Brown", LocalDate.of(1992, 11, 30), "Female", sales);
        Employee emp5 = new Employee("David", "Jones", LocalDate.of(1988, 9, 5), "Male", marketing);
        Employee emp6 = new Employee("Olivia", "Davis", LocalDate.of(1995, 4, 12), "Female", hr);
        Employee emp7 = new Employee("James", "Wilson", LocalDate.of(1983, 3, 25), "Male", it);
        Employee emp8 = new Employee("Sophia", "Anderson", LocalDate.of(1991, 8, 17), "Female", sales);
        Employee emp9 = new Employee("Liam", "Miller", LocalDate.of(1979, 12, 1), "Male", hr);
        Employee emp10 = new Employee("Emma", "Taylor", LocalDate.of(1993, 6, 28), "Female", marketing);

        emp1 = employeeService.saveEmployee(emp1);
        emp2 = employeeService.saveEmployee(emp2);
        emp3 = employeeService.saveEmployee(emp3);
        emp4 = employeeService.saveEmployee(emp4);
        emp5 = employeeService.saveEmployee(emp5);
        emp6 = employeeService.saveEmployee(emp6);
        emp7 = employeeService.saveEmployee(emp7);
        emp8 = employeeService.saveEmployee(emp8);
        emp9 = employeeService.saveEmployee(emp9);
        emp10 = employeeService.saveEmployee(emp10);

        // Create payments
        employeeService.savePayment(
                new Payment(emp2, new BigDecimal("65784.00"), LocalDateTime.of(2025, 1, 1, 13, 44, 12, 824000000)));
        employeeService.savePayment(
                new Payment(emp4, new BigDecimal("62736.00"), LocalDateTime.of(2025, 1, 6, 18, 36, 37, 892000000)));
        employeeService.savePayment(
                new Payment(emp1, new BigDecimal("69437.00"), LocalDateTime.of(2025, 1, 1, 10, 19, 21, 563000000)));
        employeeService.savePayment(
                new Payment(emp3, new BigDecimal("67183.00"), LocalDateTime.of(2025, 1, 2, 17, 21, 57, 341000000)));
        employeeService.savePayment(
                new Payment(emp2, new BigDecimal("66273.00"), LocalDateTime.of(2025, 2, 1, 11, 49, 15, 764000000)));
        employeeService.savePayment(
                new Payment(emp5, new BigDecimal("71475.00"), LocalDateTime.of(2025, 1, 1, 7, 24, 14, 453000000)));
        employeeService.savePayment(
                new Payment(emp1, new BigDecimal("70837.00"), LocalDateTime.of(2025, 2, 3, 19, 11, 31, 553000000)));
        employeeService.savePayment(
                new Payment(emp6, new BigDecimal("69628.00"), LocalDateTime.of(2025, 1, 2, 10, 41, 15, 113000000)));
        employeeService.savePayment(
                new Payment(emp4, new BigDecimal("71876.00"), LocalDateTime.of(2025, 2, 1, 12, 16, 47, 807000000)));
        employeeService.savePayment(
                new Payment(emp3, new BigDecimal("70098.00"), LocalDateTime.of(2025, 2, 3, 10, 11, 17, 341000000)));
        employeeService.savePayment(
                new Payment(emp6, new BigDecimal("67827.00"), LocalDateTime.of(2025, 2, 2, 19, 21, 27, 753000000)));
        employeeService.savePayment(
                new Payment(emp5, new BigDecimal("69871.00"), LocalDateTime.of(2025, 2, 5, 17, 54, 17, 453000000)));
        employeeService.savePayment(
                new Payment(emp2, new BigDecimal("72984.00"), LocalDateTime.of(2025, 3, 5, 9, 37, 35, 974000000)));
        employeeService.savePayment(
                new Payment(emp1, new BigDecimal("67982.00"), LocalDateTime.of(2025, 3, 1, 6, 9, 51, 983000000)));
        employeeService.savePayment(
                new Payment(emp6, new BigDecimal("70198.00"), LocalDateTime.of(2025, 3, 2, 10, 34, 35, 753000000)));
        employeeService.savePayment(
                new Payment(emp4, new BigDecimal("74998.00"), LocalDateTime.of(2025, 3, 2, 9, 27, 26, 162000000)));

        System.out.println("Sample data initialized successfully!");
    }
}
