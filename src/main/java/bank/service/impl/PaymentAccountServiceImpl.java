package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.Bank;
import main.java.bank.entity.PaymentAccount;
import main.java.bank.entity.User;
import main.java.bank.service.BankService;
import main.java.bank.service.PaymentAccountService;
import main.java.bank.service.UserService;

import java.util.Collection;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private BankRepository rep;
    private UserService userService;
    private BankService bankService;
    public PaymentAccountServiceImpl(BankRepository rep, UserService userService,BankService bankService){
        this.rep = rep;
        this.userService = userService;
        this.bankService = bankService;
    }
    public PaymentAccount getPaymentAccount(int id) {
        return rep.paymentAccounts.get(id);
    }
    public Collection<PaymentAccount> getAll() {
        return rep.paymentAccounts.get();
    }
    public PaymentAccount addPaymentAccount(PaymentAccount paymentAcc) {
        try {
            rep.paymentAccounts.add(paymentAcc);
            return paymentAcc;
        }
        catch (Exception ex) {
            System.out.println("Ошибка при добавлении платежного счета: " + ex.getMessage());
            return null;
        }
    }
    public PaymentAccount updatePaymentAccount(PaymentAccount model) {
        try {
            return rep.paymentAccounts.update(model);
        }
        catch(Exception ex) {
            System.out.println("Ошибка при изменении платежного счета: " + ex.getMessage());
            return null;
        }
    }
    public PaymentAccount openPaymentAccount(int userId, int bankId, double initialSumm) {
        PaymentAccount paymentAccount = new PaymentAccount();
        User user = userService.getUser(userId);
        Bank bank = bankService.get(bankId);

        paymentAccount.user = user;
        paymentAccount.bankName = bank.name;
        paymentAccount.moneyCount = initialSumm;

        bank.paymentAccounts.add(paymentAccount);
        user.paymentAccounts.add(paymentAccount);

        userService.updateUser(user);

        return this.addPaymentAccount(paymentAccount);
    }
}
