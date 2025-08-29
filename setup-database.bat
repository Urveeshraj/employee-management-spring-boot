@echo off
echo Employee Management System - Database Setup
echo ===========================================
echo.

REM Check if MySQL is accessible
mysql --version >nul 2>&1
if errorlevel 1 (
    echo MySQL not found in PATH. Please install MySQL and add it to your PATH.
    echo Download MySQL from: https://dev.mysql.com/downloads/installer/
    pause
    exit /b 1
)

echo MySQL found!
echo.

set /p username=Enter MySQL username (default: root): 
if "%username%"=="" set username=root

set /p password=Enter MySQL password: 

echo.
echo Creating database and tables...

mysql -u %username% -p%password% < database_setup.sql

if errorlevel 1 (
    echo Error setting up database. Please check your MySQL credentials and try again.
    echo You can also manually execute the SQL script in MySQL Workbench or command line.
) else (
    echo Database setup completed successfully!
    echo.
    echo Database: employee_db
    echo Tables created: DEPARTMENT, EMPLOYEE, PAYMENTS
    echo Sample data inserted successfully!
)

echo.
echo Next steps:
echo 1. Update application.properties with your MySQL credentials
echo 2. Run: mvnw.cmd spring-boot:run
echo 3. The application will start on http://localhost:8080

pause
