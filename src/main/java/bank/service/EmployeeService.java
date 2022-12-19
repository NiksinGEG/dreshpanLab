package main.java.bank.service;

import main.java.bank.entity.Employee;
import main.java.bank.exceptions.NotFoundException;

public interface EmployeeService {
    Employee getEmployee(int id) throws NotFoundException;
    Employee addEmployee(Employee entity);
    Employee updateEmployee(Employee model);
}
