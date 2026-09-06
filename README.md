# Employee Management System

A complete console-based Java project demonstrating **Core Java, OOP, JDBC and MySQL**.

## Features
- Add employee
- View all employees
- Search by name, email, department or designation
- Update employee details
- Delete employee
- Update salary
- Filter by department
- MySQL persistence
- JDBC PreparedStatement
- DAO design pattern

## Tech Stack
Java 17 | JDBC | MySQL 8+ | Maven | SQL | OOP

## Project Structure
```text
employee-management-system/
├── database/
│   └── schema.sql
├── src/main/java/com/mohan/ems/
│   ├── dao/
│   │   └── EmployeeDAO.java
│   ├── model/
│   │   └── Employee.java
│   ├── util/
│   │   └── DatabaseConnection.java
│   └── Main.java
├── src/main/resources/
│   └── db.properties
├── pom.xml
└── README.md
```

## Setup

### 1. Install
- JDK 17+
- Maven
- MySQL 8+

### 2. Create database
Run:
```sql
SOURCE database/schema.sql;
```

This creates `employee_db` and the `employees` table.

### 3. Configure MySQL
Edit `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC
db.username=root
db.password=root
```

Use your own MySQL password.

### 4. Run
```bash
mvn clean compile
mvn exec:java
```

Or run `com.mohan.ems.Main` from IntelliJ IDEA/Eclipse.

## Main Operations
1. Add Employee
2. View All Employees
3. Search Employee
4. Update Employee
5. Delete Employee
6. Update Salary
7. View Employees by Department
8. Exit

## OOP Concepts
- **Encapsulation:** private Employee fields with getters/setters.
- **Classes & Objects:** Employee objects represent records.
- **Abstraction:** EmployeeDAO hides database implementation.
- **Separation of concerns:** UI, model, DAO and connection logic are separated.

## JDBC Concepts
Connection, PreparedStatement, ResultSet, INSERT, SELECT, UPDATE, DELETE, generated keys and try-with-resources.

## Resume Description

**Employee Management System | Java, JDBC, MySQL**
- Developed a console-based Employee Management System using Java, JDBC and MySQL.
- Implemented CRUD operations for employee records with search and department-based filtering.
- Applied OOP concepts including encapsulation, classes, objects and separation of responsibilities.
- Implemented salary management and employee-detail updates using JDBC PreparedStatement.
- Designed a DAO layer to separate database operations from application logic.
- Used MySQL for persistent employee data storage and SQL-based data management.

## Interview Explanation

The application allows an organization to add, search, update, delete and manage employee records. The Main class handles user input, Employee represents the data model, EmployeeDAO handles SQL operations, and DatabaseConnection manages JDBC connections. PreparedStatement is used for parameterized database queries.

## Future Enhancements
- Java Swing/JavaFX GUI
- Login and role-based access
- Spring Boot REST API
- Employee attendance
- Leave management
- Payroll module
- Export to Excel/PDF
- Unit testing
