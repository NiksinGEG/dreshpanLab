package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.User;
import main.java.bank.service.UserService;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private BankRepository rep;
    public UserServiceImpl(BankRepository rep) {
        this.rep = rep;
    }
    public User getUser(int id) {
        return rep.users.get(id);
    }
    public Collection<User> getAll() {
        return rep.users.get();
    }
    public User addUser(User user) {
        try {
            rep.users.add(user);
            return user;
        }
        catch(Exception ex) {
            System.out.println("Ошибка при добавлении пользователя: " + ex.getMessage());
            return null;
        }
    }
    @Override
    public User updateUser(User model) {
        try {
            return rep.users.update(model);
        }
        catch(Exception ex) {
            System.out.println("Ошибка при изменении данных пользователя: " + ex.getMessage());
            return null;
        }
    }
}
