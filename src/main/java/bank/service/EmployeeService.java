package main.java.bank.service;

import main.java.bank.entity.Employee;

public interface EmployeeService {
    Employee getEmployee(int id);
    Employee addEmployee(Employee entity);
    Employee updateEmployee(Employee model);
}
