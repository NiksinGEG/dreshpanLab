package main.java.bank.service;

import main.java.bank.entity.User;
import main.java.bank.exceptions.NotFoundException;

import java.util.Collection;

public interface UserService {
    User getUser(int id) throws NotFoundException;
    Collection<User> getAll();
    User addUser(User user);
    User updateUser(User model);
    public void sendPayAccounts(int userId, String bankName, String destination) throws Exception;
    public void sendCredAccounts(int userId, int payAccId, String destination) throws Exception;

}
