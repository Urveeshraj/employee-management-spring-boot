# Employee Management System - Spring Boot Application

This is a full-stack Spring Boot application that follows the Controller -> Service -> DAO -> Database pattern. The application automatically sends POST requests to generate webhooks and submits SQL solutions as specified in the requirements.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/urveeshproject/employee/
│   │       ├── EmployeeApplication.java          # Main application class
│   │       ├── component/
│   │       │   └── StartupWebhookRunner.java     # Runs webhook flow on startup
│   │       ├── config/
│   │       │   └── AppConfig.java                # Configuration for RestTemplate
│   │       ├── controller/
│   │       │   └── EmployeeController.java       # REST Controller layer
│   │       ├── dao/
│   │       │   └── EmployeeDao.java              # Data Access Object layer
│   │       ├── dto/
│   │       │   ├── EmployeeAgeResultDto.java     # DTO for query results
│   │       │   ├── SolutionRequestDto.java       # DTO for solution submission
│   │       │   ├── WebhookRequestDto.java        # DTO for webhook generation
│   │       │   └── WebhookResponseDto.java       # DTO for webhook response
│   │       ├── entity/
│   │       │   ├── Department.java               # Department entity
│   │       │   ├── Employee.java                 # Employee entity
│   │       │   └── Payment.java                  # Payment entity
│   │       ├── repository/
│   │       │   ├── DepartmentRepository.java     # Department repository
│   │       │   ├── EmployeeRepository.java       # Employee repository with custom query
│   │       │   └── PaymentRepository.java        # Payment repository
│   │       └── service/
│   │           ├── DataInitializationService.java # Initializes sample data
│   │           ├── EmployeeService.java          # Business logic layer
│   │           └── WebhookService.java           # Webhook operations service
│   └── resources/
│       └── application.properties                # Database and application configuration
└── database_setup.sql                           # SQL script for database setup
```

## Features

1. **Automatic Webhook Flow**: On application startup, automatically:
   - Sends POST request to generate webhook
   - Processes the SQL problem (calculating younger employees count)
   - Submits the final SQL query using JWT token

2. **Complete CRUD Operations**: REST endpoints for:
   - Employee management
   - Department management
   - Payment management

3. **SQL Problem Solution**: Implements the solution to calculate the number of employees who are younger than each employee in their respective departments.

## Prerequisites

- Java 20
- MySQL 8.0 or later
- Maven (included in the project as mvnw)

## Setup Instructions

### 1. Database Setup

1. Install and start MySQL server
2. Create the database and tables using the provided SQL script:

```bash
mysql -u root -p < database_setup.sql
```

Or manually execute the SQL commands in `database_setup.sql`

### 2. Application Configuration

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### 3. Build and Run

Since you don't have Maven installed globally, use the Maven wrapper:

```bash
# On Windows (PowerShell)
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run

# On Windows (Command Prompt)
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The application will:
- Start on port 8080
- Automatically create/update database tables
- Initialize sample data
- Execute the webhook flow automatically

## API Endpoints

### Employee Endpoints
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `POST /api/employees` - Create new employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

### Department Endpoints
- `GET /api/employees/departments` - Get all departments
- `GET /api/employees/departments/{id}` - Get department by ID
- `POST /api/employees/departments` - Create new department

### Payment Endpoints
- `GET /api/employees/payments` - Get all payments
- `POST /api/employees/payments` - Create new payment

### Solution Endpoints
- `GET /api/employees/younger-count` - Get the SQL problem solution
- `GET /api/employees/final-query` - Get the final SQL query string

## SQL Problem Solution

The application solves the problem: "Calculate the number of employees who are younger than each employee, grouped by their respective departments."

**Final SQL Query:**
```sql
SELECT e1.EMP_ID,
       e1.FIRST_NAME,
       e1.LAST_NAME,
       d.DEPARTMENT_NAME,
       COUNT(e2.EMP_ID) as YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEE e1
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
ORDER BY e1.EMP_ID DESC;
```

## Database Schema

### DEPARTMENT Table
- `DEPARTMENT_ID` (Primary Key, Auto Increment)
- `DEPARTMENT_NAME` (VARCHAR, NOT NULL)

### EMPLOYEE Table
- `EMP_ID` (Primary Key, Auto Increment)
- `FIRST_NAME` (VARCHAR, NOT NULL)
- `LAST_NAME` (VARCHAR, NOT NULL)
- `DOB` (DATE, NOT NULL)
- `GENDER` (VARCHAR, NOT NULL)
- `DEPARTMENT` (Foreign Key -> DEPARTMENT.DEPARTMENT_ID)

### PAYMENTS Table
- `PAYMENT_ID` (Primary Key, Auto Increment)
- `EMP_ID` (Foreign Key -> EMPLOYEE.EMP_ID)
- `AMOUNT` (DECIMAL(10,2), NOT NULL)
- `PAYMENT_TIME` (DATETIME, NOT NULL)

## Webhook Flow Details

1. **Startup**: `StartupWebhookRunner` executes the webhook flow when application starts
2. **Generate Webhook**: POST to `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
3. **Submit Solution**: POST to `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA` with JWT token

## Technology Stack

- **Framework**: Spring Boot 3.4.9
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA / Hibernate
- **HTTP Client**: RestTemplate
- **Build Tool**: Maven
- **Java Version**: 20

## Troubleshooting

1. **Database Connection Issues**: Verify MySQL is running and credentials are correct
2. **Port Conflicts**: Change `server.port` in application.properties if port 8080 is busy
3. **Webhook Failures**: Check internet connection and API endpoints availability

## Sample Data

The application automatically initializes with the provided sample data including:
- 6 departments (HR, Finance, Engineering, Sales, Marketing, IT)
- 10 employees with their respective departments and birth dates
- 16 payment records with timestamps

The SQL solution correctly calculates younger employees count for each employee within their department.
