package main.java.bank.entity;

import main.java.bank.base.BaseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CreditAccount extends BaseEntity {
    public User user;
    public String bankName;
    public Date dateBegin;
    public Date dateEnd;
    public int month;
    public double monthPayment;
    public double percent;
    public Employee employee;
    public PaymentAccount paymentAccount;

    public String toString() {
        return String.format("Кредитный аккаунт: Принаджежит пользователю под номером=%s;Название банка=%s;Дата начала=%s;Дата конца=%s;Месяцы=%s;Месячный платеж=%.2f;" +
                        "Процент=%.2f;Имя, кто выдал кредит=%s;Номер платежного аккаунта=%s",
                user.id,
                bankName,
                dateBegin,
                dateEnd,
                month,
                monthPayment,
                percent,
                employee.name,
                paymentAccount.id);
    }
    public static CreditAccount fromMap(Map<String, String> map) throws Exception {
        CreditAccount res = new CreditAccount();
        res.id = Integer.parseInt(map.get("id"));
        res.bankName = map.get("bankName");
        res.month = Integer.parseInt(map.get("month"));
        res.monthPayment = Double.parseDouble(map.get("monthPayment"));
        res.percent = Double.parseDouble(map.get("percent"));

        res.user = new User();
        res.user.id =  Integer.parseInt(map.get("userid"));
        res.user.name = map.get("name");
        res.user.creditRate = Integer.parseInt(map.get("creditRate"));
        res.user.workingPlace = map.get("workingPlace");

        return res;
    }
}
