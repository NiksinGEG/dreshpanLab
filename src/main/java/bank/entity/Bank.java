package main.java.bank.entity;

import main.java.bank.StringHelper;
import main.java.bank.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Bank extends BaseEntity {
    public String name;
    public int officeNum;
    public int atmNum;
    public int employeeNum;
    public int clientNum;
    public int rating;
    public double percent;
    public double totalMoneyCount;
    public Collection<BankAtm> atms;
    public Collection<BankOffice> offices;
    public Collection<User> users;
    public Collection<Employee> employees;
    public Collection<PaymentAccount> paymentAccounts;

    public Bank(){
        Random rnd = new Random();
        rating = rnd.nextInt(101);
        percent = rnd.nextDouble(11 - (rating / 10.0), 20 - (rating / 10.0));
        atms = new LinkedList<>();
        offices = new LinkedList<>();
        users = new LinkedList<>();
        employees = new LinkedList<>();
        paymentAccounts = new LinkedList<>();
    }

    public String toString(){
        return String.format("""
                        \sНазвание банка: %s; \sРейтинг: %s; \sПроцентная ставка: %.2f;""",
                name,
                rating,
                percent);

    }
}
