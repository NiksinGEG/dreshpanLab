package main.java.bank.entity;

import main.java.bank.base.BaseEntity;

import java.util.Date;

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
}
