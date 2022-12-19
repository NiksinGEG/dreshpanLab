package main.java.bank.base;

import main.java.bank.entity.*;

public class BankRepository {
    public EntityContainer<Bank> banks;
    public EntityContainer<BankAtm> atms;
    public EntityContainer<BankOffice> offices;
    public EntityContainer<CreditAccount> creditAccounts;
    public EntityContainer<Employee> employees;
    public EntityContainer<PaymentAccount> paymentAccounts;
    public EntityContainer<User> users;
    public BankRepository()
    {
        banks = new EntityContainer<>();
        atms = new EntityContainer<>();
        offices = new EntityContainer<>();
        creditAccounts = new EntityContainer<>();
        employees = new EntityContainer<>();
        paymentAccounts = new EntityContainer<>();
        users = new EntityContainer<>();
    }
}
