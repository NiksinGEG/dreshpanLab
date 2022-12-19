package main.java.bank.service;

import main.java.bank.entity.Bank;

import java.util.Collection;

public interface BankService {
    Bank get(int id);
    Collection<Bank> getAll();
    Bank addBank(Bank bank);
    Bank updateBank(Bank bank);
    Bank addAtmToBank(int bankId, int atmId) throws Exception;
    Bank addNewBankOffice(int bankId, int officeId);
    Bank addEmployeeToBank(int bankId, int employeeId);
    Bank addBankUser(int bankId, int userId);
}
