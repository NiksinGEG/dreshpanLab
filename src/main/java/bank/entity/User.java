package main.java.bank.entity;

import main.java.bank.helper.StringHelper;
import main.java.bank.base.BaseEntity;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class User extends BaseEntity {
    public String name;
    public Date birthday;
    public String workingPlace;
    private double salary;
    public int creditRate;
    public Collection<Bank> banks;
    public Collection<PaymentAccount> paymentAccounts;
    public Collection<CreditAccount> creditAccounts;

    private Random rnd;
    public User(){
        banks = new LinkedList<>();
        paymentAccounts = new LinkedList<>();
        creditAccounts = new LinkedList<>();
        rnd = new Random();
    }
    public void setSalary(double salary){
        int lowerBound = (int)salary / 10 - 100;
        if(lowerBound < 0)
            lowerBound = 0;
        this.creditRate = rnd.nextInt(lowerBound, lowerBound + 100);
        this.salary = salary;
    }

    public double getSalary(){
        return salary;
    }

    public String toString() {
        return String.format("""
                        Пользователь: 
                        \sИмя=%s;
                        \sДата рождения=%s;
                        \sМесто работы=%s;
                        \sКредитный рейтинг=%s,
                        \sЖалование=%.2f;
                        \sПлатенжные аккаунты=%s;
                        \sКредитные аккаунты=%s""",
                name,
                birthday,
                workingPlace,
                creditRate,
                salary,
                StringHelper.fromCollectionToString(paymentAccounts),
                StringHelper.fromCollectionToString(creditAccounts));
    }
}
