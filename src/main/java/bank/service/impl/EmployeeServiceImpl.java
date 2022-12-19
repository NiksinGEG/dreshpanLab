package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.CreditAccount;
import main.java.bank.entity.Employee;
import main.java.bank.exceptions.NotFoundException;
import main.java.bank.service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    private BankRepository rep;
    public EmployeeServiceImpl(BankRepository rep){
        this.rep = rep;
    }
    public Employee getEmployee(int id) throws RuntimeException{
        var res = rep.employees.get(id);
        if(res == null) throw new NotFoundException(id, Employee.class);
        return res;
    }
    public Collection<Employee> getAll() {
        return rep.employees.get();
    }
    public Employee addEmployee(Employee entity) {
        try {
            rep.employees.add(entity);
            return entity;
        }
        catch(RuntimeException ex) {
            System.out.println("Ошибка добавления сотрудника: " + ex.getMessage());
            return null;
        }
    }
    @Override
    public Employee updateEmployee(Employee model) {
        try {
            return rep.employees.update(model);
        }
        catch(RuntimeException ex) {
            System.out.println("Ошибка изменения сотрудника: " + ex.getMessage());
            return null;
        }
    }
}
