package main.java.bank.entity;

import main.java.bank.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class PaymentAccount extends BaseEntity {
    public User user;
    public String bankName;
    public double moneyCount;
    public Collection<CreditAccount> accounts;
    public PaymentAccount(){
        accounts = new LinkedList<>();
    }

    public String toString() {
        return String.format("Платежный аккаунт: %s;Уникальный номер пользователя=%s;Имя банка=%s;Количество денег=%.2f",
                id,
                user.id,
                bankName,
                moneyCount);
    }
    public static PaymentAccount fromMap(Map<String, String> map) {
        PaymentAccount res = new PaymentAccount();
        res.id = Integer.parseInt(map.get("id"));
        res.bankName = map.get("bankName");

        res.user = new User();
        res.user.id =  Integer.parseInt(map.get("userid"));
        res.user.name = map.get("name");
        res.user.creditRate = Integer.parseInt(map.get("creditRate"));
        res.user.workingPlace = map.get("workingPlace");

        res.moneyCount = Double.parseDouble(map.get("moneyCount"));
        return res;
    }
}
