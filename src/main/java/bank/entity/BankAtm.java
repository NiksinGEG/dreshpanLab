package main.java.bank.entity;

import main.java.bank.base.BaseEntity;
import main.java.bank.entity.enums.AtmStatuses;

public class BankAtm extends BaseEntity {
    public String name;
    public String adress;
    public AtmStatuses status;
    public Bank bank;
    public BankOffice placingOffice;
    public Employee employee;
    public boolean isGiveMoney;
    public boolean isTakeMoney;
    private double moneyCount;
    public double servicePrice;

    public double getMoneyCount(){
        return moneyCount;
    }
    public void setMoneyCount(double moneyAmount) {
        if(this.moneyCount == 0 && moneyAmount > 0)
            if(!(status == AtmStatuses.notWorking))
                status = AtmStatuses.working;
        this.moneyCount = moneyAmount;
        if(this.moneyCount <= 0)
        {
            this.moneyCount = 0;
            if(!(status == AtmStatuses.notWorking))
            {
                status = AtmStatuses.noMoney;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Банкомат: Название=%s;Адрес=%s;Статус=%s;Принимает деньги=%s;Выдает деньги=%s;Количество денег=%.2f;Цена обслуживания=%.2f",
                name,
                adress,
                status,
                isTakeMoney,
                isGiveMoney,
                moneyCount,
                servicePrice);
    }
}
