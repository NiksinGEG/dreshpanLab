package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.*;
import main.java.bank.service.*;

import java.util.Collection;

public class BankServiceImpl implements BankService {
    private BankRepository rep;
    private AtmService atmService;
    private BankOfficeService bankOfficeService;
    private EmployeeService employeeService;
    private UserService userService;
    public BankServiceImpl(BankRepository rep, AtmService atmService, BankOfficeService bankOfficeService, EmployeeService employeeService, UserService userService) {
        this.rep = rep;
        this.atmService = atmService;
        this.bankOfficeService = bankOfficeService;
        this.employeeService = employeeService;
        this.userService = userService;
    }
    public Bank get(int id) {
        return rep.banks.get(id);
    }

    public Collection<Bank> getAll() {
        return rep.banks.get();
    }

    public Bank addBank(Bank bank) {
        try {
            rep.banks.add(bank);
            return bank;
        }
        catch(Exception ex) {
            System.out.println("Ошибка добавления банка: " + ex.getMessage());
            return null;
        }
    }

    public Bank updateBank(Bank b) {
        try {
            return rep.banks.update(b);
        }
        catch(Exception ex) {
            System.out.println("Ошибка при изменении банка: " + ex.getMessage());
            return null;
        }
    }
    public Bank addAtmToBank(int bankId, int atmId) throws Exception {
        Bank bank = this.get(bankId);
        BankAtm atm = atmService.getAtm(atmId);

        if(bank.atms.contains(atm))
            throw new Exception("Банкомат с id = " + atm.id + " уже имеется в банке");

        atm.bank = bank;
        bank.atms.add(atm);
        bank.atmNum++;

        atmService.updateAtm(atm);
        return updateBank(bank);
    }
    public Bank addNewBankOffice(int bankId, int officeId) {
        Bank bank = this.get(bankId);
        BankOffice office = bankOfficeService.getOffice(officeId);

        bank.officeNum++;
        bank.offices.add(office);

        return updateBank(bank);
    }

    public Bank addEmployeeToBank(int bankId, int employeeId) {
        Bank bank = this.get(bankId);
        Employee employee = employeeService.getEmployee(employeeId);

        employee.bank = bank;
        bank.employeeNum++;
        bank.employees.add(employee);

        employeeService.updateEmployee(employee);
        return updateBank(bank);
    }

    public Bank addBankUser(int bankId, int userId) {
        Bank bank = this.get(bankId);
        User user = userService.getUser(userId);

        user.banks.add(bank);
        bank.clientNum++;
        bank.users.add(user);

        userService.updateUser(user);
        return updateBank(bank);
    }
}
