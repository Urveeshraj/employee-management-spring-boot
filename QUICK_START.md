# Quick Start Guide

## Prerequisites Setup

1. **Install MySQL 8.0+**
   - Download from: https://dev.mysql.com/downloads/installer/
   - During installation, set root password and remember it
   - Ensure MySQL is added to your system PATH

2. **Verify Java 20**
   ```powershell
   java -version
   ```

## Quick Setup (3 steps)

### Step 1: Setup Database
Choose one of these methods:

**Option A: Using PowerShell script**
```powershell
.\setup-database.ps1
```

**Option B: Using Batch script**
```cmd
setup-database.bat
```

**Option C: Manual MySQL setup**
```bash
mysql -u root -p
```
Then execute the contents of `database_setup.sql`

### Step 2: Configure Database Connection
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Run Application
```powershell
.\mvnw.cmd spring-boot:run
```

## What Happens When You Start

1. ✅ Application starts on port 8080
2. ✅ Database tables are created/updated automatically
3. ✅ Sample data is initialized
4. ✅ **Webhook flow executes automatically:**
   - Sends POST to generate webhook
   - Solves the SQL problem
   - Submits solution with JWT token
5. ✅ REST API becomes available

## Test the Application

### Check the SQL Solution
```bash
curl http://localhost:8080/api/employees/younger-count
```

### Get All Employees
```bash
curl http://localhost:8080/api/employees
```

### View Final SQL Query
```bash
curl http://localhost:8080/api/employees/final-query
```

## Final SQL Query Solution

The application solves this problem: *"Calculate the number of employees who are younger than each employee, grouped by their respective departments."*

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

## Application Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │───▶│     Service     │───▶│      DAO        │───▶│    Database     │
│                 │    │                 │    │                 │    │                 │
│ EmployeeController   │ EmployeeService │    │   EmployeeDao   │    │   MySQL DB      │
│                 │    │ WebhookService  │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
```

## Troubleshooting

**Database Connection Error:**
- Verify MySQL is running: `mysqladmin ping -u root -p`
- Check credentials in application.properties

**Port 8080 in use:**
- Change port in application.properties: `server.port=8081`

**Webhook fails:**
- Check internet connection
- Application continues normally even if webhook fails

## Success Indicators

Look for these messages in the console:
- ✅ "Sample data initialized successfully!"
- ✅ "Application started - initiating webhook flow..."
- ✅ "Webhook flow completed successfully!"
