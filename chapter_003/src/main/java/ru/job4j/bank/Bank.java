package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<User, List<Account>> bank = new HashMap<>();

    public void addUser(User user) {
        bank.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        bank.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        
    }
}
