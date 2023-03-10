package main.java.bank.service;

import main.java.bank.entity.CreditAccount;

import java.util.Collection;

public interface CreditAccounService {
    CreditAccount getCreditAccount(int id);
    Collection<CreditAccount> getAll();
    CreditAccount addCreditAccount(CreditAccount creditAcc);
    CreditAccount updateCreditAccount(CreditAccount model);
    CreditAccount openCreditAccount(int userId, int bankId, int employeeId, int paymentAccountId, double monthPayment, int months);
}
