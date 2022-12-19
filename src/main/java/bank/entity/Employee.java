package main.java.bank.entity;

import main.java.bank.base.BaseEntity;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class Employee extends BaseEntity {
    public String name;
    public Date birthday;
    public String post;
    public Bank bank;
    public boolean isWorkDistance;
    public BankOffice office;
    public boolean canGiveCredit;
    public double salary;
    public Collection<BankAtm> servingAtms;
    public Collection<CreditAccount> accounts;

    public Employee() {
        servingAtms = new LinkedList<>();
        accounts = new LinkedList<>();
    }
    public String toString() {
        return String.format("Работник: Имя=%s;Дата рождения=%s;Должность=%s;Работает удаленно=%s;Номер офиса, в котором работает=%s;" +
                        "Может выдать кредит=%s;Жалование=%.2f",
                name,
                birthday,
                post,
                isWorkDistance,
                office.id,
                canGiveCredit,
                salary);
    }
}
