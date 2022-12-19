package main.java.bank.service;

import main.java.bank.entity.PaymentAccount;

import java.util.Collection;

public interface PaymentAccountService {
    PaymentAccount getPaymentAccount(int id) throws RuntimeException;
    Collection<PaymentAccount> getAll();
    PaymentAccount addPaymentAccount(PaymentAccount paymentAcc);
    PaymentAccount updatePaymentAccount(PaymentAccount model);
    PaymentAccount openPaymentAccount(int userId, int bankId, double initialSumm);
}
