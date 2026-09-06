package com.mohan.ems;

import com.mohan.ems.dao.EmployeeDAO;
import com.mohan.ems.model.Employee;
import com.mohan.ems.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeDAO dao = new EmployeeDAO();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("========================================");

        if (!DatabaseConnection.testConnection()) {
            System.out.println("Database connection failed. Check MySQL and db.properties.");
            return;
        }

        boolean running = true;
        while (running) {
            menu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> print(dao.getAllEmployees());
                    case 3 -> search();
                    case 4 -> update();
                    case 5 -> delete();
                    case 6 -> salary();
                    case 7 -> department();
                    case 8 -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        System.out.println("Thank you for using the system.");
        scanner.close();
    }

    private static void menu() {
        System.out.println("\n--------------- MENU ----------------");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Update Salary");
        System.out.println("7. View Employees by Department");
        System.out.println("8. Exit");
        System.out.println("-------------------------------------");
    }

    private static void addEmployee() {
        Employee e = readEmployee();
        int id = dao.addEmployee(e);
        System.out.println("Employee added successfully. ID: " + id);
    }

    private static void search() {
        String key = readString("Enter name/email/department/designation: ");
        print(dao.searchEmployees(key));
    }

    private static void update() {
        int id = readInt("Enter employee ID: ");
        if (dao.getEmployeeById(id) == null) {
            System.out.println("Employee not found.");
            return;
        }
        Employee e = readEmployee();
        e.setId(id);
        System.out.println(dao.updateEmployee(e) ?
                "Employee updated successfully." : "Update failed.");
    }

    private static void delete() {
        int id = readInt("Enter employee ID: ");
        System.out.println(dao.deleteEmployee(id) ?
                "Employee deleted successfully." : "Employee not found.");
    }

    private static void salary() {
        int id = readInt("Enter employee ID: ");
        BigDecimal salary = readDecimal("Enter new salary: ");
        System.out.println(dao.updateSalary(id, salary) ?
                "Salary updated successfully." : "Employee not found.");
    }

    private static void department() {
        String dept = readString("Enter department: ");
        print(dao.getEmployeesByDepartment(dept));
    }


    private static Employee readEmployee() {
        String name = readString("Name: ");
        String email = readString("Email: ");
        String phone = readString("Phone: ");
        String department = readString("Department: ");
        String designation = readString("Designation: ");
        BigDecimal salary = readDecimal("Salary: ");
        LocalDate date = readDate();

        return new Employee(0, name, email, phone, department, designation,
                salary, Date.valueOf(date));
    }

    private static void print(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.printf("%n%-4s %-18s %-25s %-14s %-18s %-12s %-12s%n",
                "ID", "NAME", "EMAIL", "DEPARTMENT", "DESIGNATION", "SALARY", "JOIN DATE");
        System.out.println("-".repeat(110));
        for (Employee e : list) {
            System.out.printf("%-4d %-18s %-25s %-14s %-18s %-12s %-12s%n",
                    e.getId(), e.getName(), e.getEmail(), e.getDepartment(),
                    e.getDesignation(), e.getSalary(), e.getJoiningDate());
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static BigDecimal readDecimal(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount.");
            }
        }
    }

    private static LocalDate readDate() {
        while (true) {
            try {
                System.out.print("Joining date (YYYY-MM-DD): ");
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Use YYYY-MM-DD.");
            }
        }
    }
}