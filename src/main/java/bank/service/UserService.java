package main.java.bank.service;

import main.java.bank.entity.User;

import java.util.Collection;

public interface UserService {
    User getUser(int id);
    Collection<User> getAll();
    User addUser(User user);
    User updateUser(User model);
}
