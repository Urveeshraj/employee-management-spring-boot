# MySQL Database Setup Script for Employee Management System
# Run this script in PowerShell after installing MySQL

Write-Host "Employee Management System - Database Setup" -ForegroundColor Green
Write-Host "===========================================" -ForegroundColor Green

# Check if MySQL is accessible
try {
    mysql --version
    Write-Host "MySQL found!" -ForegroundColor Green
} catch {
    Write-Host "MySQL not found in PATH. Please install MySQL and add it to your PATH." -ForegroundColor Red
    Write-Host "Download MySQL from: https://dev.mysql.com/downloads/installer/" -ForegroundColor Yellow
    exit 1
}

# Get MySQL credentials
$username = Read-Host "Enter MySQL username (default: root)"
if ([string]::IsNullOrEmpty($username)) {
    $username = "root"
}

$password = Read-Host "Enter MySQL password" -AsSecureString
$plainPassword = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($password))

Write-Host "`nCreating database and tables..." -ForegroundColor Yellow

# Execute the SQL script
try {
    mysql -u $username -p$plainPassword < "database_setup.sql"
    Write-Host "Database setup completed successfully!" -ForegroundColor Green
    Write-Host "`nDatabase: employee_db" -ForegroundColor Cyan
    Write-Host "Tables created: DEPARTMENT, EMPLOYEE, PAYMENTS" -ForegroundColor Cyan
    Write-Host "Sample data inserted successfully!" -ForegroundColor Cyan
} catch {
    Write-Host "Error setting up database. Please check your MySQL credentials and try again." -ForegroundColor Red
    Write-Host "You can also manually execute the SQL script in MySQL Workbench or command line." -ForegroundColor Yellow
}

Write-Host "`nNext steps:" -ForegroundColor Yellow
Write-Host "1. Update application.properties with your MySQL credentials" -ForegroundColor White
Write-Host "2. Run: .\mvnw.cmd spring-boot:run" -ForegroundColor White
Write-Host "3. The application will start on http://localhost:8080" -ForegroundColor White

Read-Host "`nPress Enter to exit"
