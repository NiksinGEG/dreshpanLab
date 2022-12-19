package main.java.bank.entity;

import main.java.bank.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;

public class PaymentAccount extends BaseEntity {
    public User user;
    public String bankName;
    public double moneyCount;
    public Collection<CreditAccount> accounts;
    public PaymentAccount(){
        accounts = new LinkedList<>();
    }

    public String toString() {
        return String.format("Платежный аккаунт: Уникальный номер пользователя=%s;Имя банка=%s;Количество денег=%.2f",
                user.id,
                bankName,
                moneyCount);
    }
}
