package main.java.bank.service;

import main.java.bank.entity.BankAtm;
import main.java.bank.entity.BankOffice;
import main.java.bank.entity.Employee;

import java.util.Collection;

public interface AtmService {
    public BankAtm getAtm(int id);
    public Collection<BankAtm> getAllAtms();
    public BankAtm addAtm(BankAtm atm);
    public BankAtm updateAtm(BankAtm atm);
    public void placeToOffice(BankAtm atm, BankOffice office) throws Exception;
    public BankAtm setEmployee(int atmId, Employee employee) throws Exception;
    public double takeMoney(int atmId, double count);
    public double depositMoney(int atmId, double amount);
}
