package com.urveeshproject.employee.repository;

import com.urveeshproject.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = """
            SELECT e1.EMP_ID as empId,
                   e1.FIRST_NAME as firstName,
                   e1.LAST_NAME as lastName,
                   d.DEPARTMENT_NAME as departmentName,
                   COUNT(e2.EMP_ID) as youngerEmployeesCount
            FROM EMPLOYEE e1
            JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
            LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB
            GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
            ORDER BY e1.EMP_ID DESC
            """, nativeQuery = true)
    List<Object[]> findEmployeesWithYoungerCount();
}
