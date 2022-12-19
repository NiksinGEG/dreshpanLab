package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.*;
import main.java.bank.service.*;

import java.util.Collection;
import java.util.Date;

public class CreditAccountServiceImpl implements CreditAccounService {
    private BankRepository rep;
    private UserService userService;
    private BankService bankService;
    private EmployeeService employeeService;
    private PaymentAccountService paymentAccountService;
    public CreditAccountServiceImpl(BankRepository rep, UserService userService, BankService bankService, EmployeeService employeeService, PaymentAccountService paymentAccountService) {
        this.rep = rep;
        this.userService = userService;
        this.bankService = bankService;
        this.employeeService = employeeService;
        this.paymentAccountService = paymentAccountService;
    }
    public CreditAccount getCreditAccount(int id) {
        return rep.creditAccounts.get(id);
    }

    public Collection<CreditAccount> getAll() {
        return rep.creditAccounts.get();
    }

    public CreditAccount addCreditAccount(CreditAccount creditAcc) {
        try {
            rep.creditAccounts.add(creditAcc);
            return creditAcc;
        }
        catch (Exception ex) {
            System.out.println("Ошибка добавления кредитного счета: " + ex.getMessage());
            return null;
        }
    }

    public CreditAccount updateCreditAccount(CreditAccount model) {
        try {
            return rep.creditAccounts.update(model);
        }
        catch(Exception ex) {
            System.out.println("Ошибка изменения кредитного счета: " + ex.getMessage());
            return null;
        }
    }
    public CreditAccount openCreditAccount(int userId, int bankId, int employeeId, int paymentAccountId, double monthPayment,
    int months) {
        User user = userService.getUser(userId);
        Bank bank = bankService.get(bankId);
        Employee employee = employeeService.getEmployee(employeeId);
        PaymentAccount paymentAccount = paymentAccountService.getPaymentAccount(paymentAccountId);
        CreditAccount creditAccount = new CreditAccount();

        creditAccount.bankName = bank.name;
        creditAccount.percent = bank.percent;
        creditAccount.user = user;
        creditAccount.employee = employee;
        creditAccount.paymentAccount = paymentAccount;
        creditAccount.month = months;
        creditAccount.monthPayment = monthPayment;
        creditAccount.dateBegin = new Date();
        creditAccount.dateEnd = creditAccount.dateBegin;

        user.creditAccounts.add(creditAccount);
        employee.accounts.add(creditAccount);
        paymentAccount.accounts.add(creditAccount);

        userService.updateUser(user);
        employeeService.updateEmployee(employee);
        paymentAccountService.updatePaymentAccount(paymentAccount);

        return this.addCreditAccount(creditAccount);
    }
}
