package main.java.bank.entity;

import main.java.bank.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;

public class BankOffice extends BaseEntity {
    public String name;
    public String address;
    public boolean isWorking;
    public boolean canSetAtm;
    public int countAtm;
    public boolean isGiveCredits;
    public boolean isGiveMoney;
    public boolean isTakeMoney;
    public double moneyCount;
    public double rentPrice;
    public Collection<Employee> employees;
    public Collection<BankAtm> bankAtms;
    public BankOffice(){
        employees = new LinkedList<>();
        bankAtms = new LinkedList<>();
    }
    @Override
    public String toString() {
        return String.format("Офис банка: Адрес=%s;Работает=%s;Возможность поставить банкомат=%s;Кол-во банкоматов=%s;Выдает кредиты=%s;" +
                        "Выдает деньги=%s;Принимает деньги=%s;Количество денег=%s;Стоимость аренды=%.2f",
                name,
                address,
                isWorking,
                canSetAtm,
                countAtm,
                isGiveCredits,
                isGiveMoney,
                isTakeMoney,
                moneyCount,
                rentPrice);
    }
}
