package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.BankAtm;
import main.java.bank.entity.BankOffice;
import main.java.bank.entity.Employee;
import main.java.bank.exceptions.NotFoundException;
import main.java.bank.service.BankOfficeService;
import main.java.bank.service.EmployeeService;

import java.util.Collection;

public class BankOfficeServiceImpl implements BankOfficeService {
    private BankRepository rep;
    private EmployeeService employeeService;

    public BankOfficeServiceImpl(BankRepository rep, EmployeeService employeeService) {
        this.rep = rep;
        this.employeeService = employeeService;
    }
    public BankOffice getOffice(int id) throws NotFoundException {
        var res = rep.offices.get(id);
        if(res == null) throw new NotFoundException(id, BankOffice.class);
        return res;
    }
    public Collection<BankOffice> getAllOffices() {
        return rep.offices.get();
    }
    public BankOffice addOffice(BankOffice office) {
        try {
            rep.offices.add(office);
            return office;
        }
        catch(RuntimeException ex) {
            System.out.println("Ошибка добавления офиса: " + ex.getMessage());
            return null;
        }
    }
    public BankOffice updateBankOffice(BankOffice office) {
        try {
            return rep.offices.update(office);
        }
        catch(RuntimeException ex) {
            System.out.println("Ошибка изменения офиса: " + ex.getMessage());
            return null;
        }
    }
    public BankOffice addEmployeeToOffice(int officeId, int employeeId) {
        BankOffice office = this.getOffice(officeId);
        Employee empl = employeeService.getEmployee(employeeId);

        office.employees.add(empl);
        empl.office = office;

        employeeService.updateEmployee(empl);
        return this.updateBankOffice(office);
    }
}
