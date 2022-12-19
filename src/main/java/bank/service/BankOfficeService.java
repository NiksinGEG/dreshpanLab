package main.java.bank.service;

import main.java.bank.entity.BankOffice;
import main.java.bank.exceptions.NotFoundException;

import java.util.Collection;

public interface BankOfficeService {
    BankOffice getOffice(int id) throws NotFoundException;
    Collection<BankOffice> getAllOffices();
    BankOffice addOffice(BankOffice office);
    BankOffice updateBankOffice(BankOffice office);
    BankOffice addEmployeeToOffice(int officeId, int employeeId);
}
