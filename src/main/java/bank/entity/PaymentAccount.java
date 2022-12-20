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
        //res.user = User.fromMap(map.get("user"));
        res.moneyCount = Double.parseDouble(map.get("moneyAmount"));
        return res;
    }
}
