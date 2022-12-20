package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.CreditAccount;
import main.java.bank.entity.PaymentAccount;
import main.java.bank.entity.User;
import main.java.bank.exceptions.NotFoundException;
import main.java.bank.helper.FileHelper;
import main.java.bank.helper.Serializer;
import main.java.bank.service.UserService;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private BankRepository rep;
    public UserServiceImpl(BankRepository rep) {
        this.rep = rep;
    }
    public User getUser(int id) {
        var res = rep.users.get(id);
        if(res == null) throw new NotFoundException(id, User.class);
        return res;
    }
    public Collection<User> getAll() {
        return rep.users.get();
    }
    public User addUser(User user) {
        try {
            rep.users.add(user);
            return user;
        }
        catch(RuntimeException ex) {
            System.out.println("Ошибка при добавлении пользователя: " + ex.getMessage());
            return null;
        }
    }
    @Override
    public User updateUser(User model) {
        try {
            return rep.users.update(model);
        }
        catch(RuntimeException ex) {
            System.out.println("Ошибка при изменении данных пользователя: " + ex.getMessage());
            return null;
        }
    }
    @Override
    public void sendPayAccounts(int userId, String bankName, String destination) throws Exception {
        User user = getUser(userId);
        Collection<PaymentAccount> paymentAccounts = user.paymentAccounts.stream().filter(x -> x.bankName == bankName).toList();
        if(paymentAccounts.isEmpty()) return;
        String toSend = Serializer.serialize(paymentAccounts);
        FileHelper.send(destination, toSend);
    }
    @Override
    public void sendCredAccounts(int userId, int payAccId, String destination) throws Exception {
        User user = getUser(userId);
        PaymentAccount payAcc = user.paymentAccounts.stream().filter(x -> x.id == payAccId).findFirst().orElseThrow();
        Collection<CreditAccount> credAccs = payAcc.accounts;
        if(credAccs.isEmpty()) return;
        String toSend = Serializer.serialize(credAccs);
        FileHelper.send(destination, toSend);
    }
}
