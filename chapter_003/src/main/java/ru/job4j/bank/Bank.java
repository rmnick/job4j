package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private Map<User, List<Account>> bank = new HashMap<>();

    public void addUser(User user) {
        bank.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        bank.remove(user);
    }

    public Set<User> getUsers() {
        return this.bank.keySet();
    }

    /*
    public void addAccountToUser(String passport, Account account) {
        Set<User> ru.job4j.set = bank.keySet();
        for (User key : ru.job4j.set) {
            if (key.getPassport().equals(passport)) {
                bank.get(key).add(account);
                break;
            }
        }
    }
    */
    public void addAccountToUser(String passport, Account account) {
        bank.keySet().stream()
                .filter(key -> key.getPassport().equals(passport))
                .forEach(key -> bank.get(key).add(account));
    }
    /*
    public void deleteAccountFromUser(String passport, Account account) {
        Set<User> ru.job4j.set = bank.keySet();
        for (User key : ru.job4j.set) {
            if (key.getPassport().equals(passport)) {
                bank.get(key).remove(account);
                break;
            }
        }
    }
    */
    public void deleteAccountFromUser(String passport, Account account) {
        bank.keySet().stream()
                .filter(key -> key.getPassport().equals(passport))
                .forEach(key -> bank.get(key).remove(account));
    }
    /*
    public List<Account> getUserAccounts(String passport) {
        List<Account> ru.job4j.list = new ArrayList<>();
        Set<User> ru.job4j.set = bank.keySet();
        for (User key : ru.job4j.set) {
            if (key.getPassport().equals(passport)) {
                ru.job4j.list = bank.get(key);
                break;
            }
        }
        return ru.job4j.list;
    }
    */
    public List<Account> getUserAccounts(String passport) {
        return bank.keySet().stream()
                .filter(key -> key.getPassport().equals(passport))
                .map(key -> bank.get(key)).findFirst().get();
    }
    /*
        private Account getUserAccount(String passport, String requisite) {
        Account account = null;
        List<Account> ru.job4j.list = this.getUserAccounts(passport);
        if (ru.job4j.list != null) {
            for (int i = 0; i < ru.job4j.list.size(); i++) {
                if (ru.job4j.list.get(i).getRequisites().equals(requisite)) {
                    account = ru.job4j.list.get(i);
                    break;
                }
            }
        }
        return account;
    }
    */
    private Account getUserAccount(String passport, String requisite) {
        return this.getUserAccounts(passport).stream()
                    .filter(account -> account.getRequisites().equals(requisite))
                    .findFirst()
                    .get();
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account src = getUserAccount(srcPassport, srcRequisite);
        Account dst = getUserAccount(dstPassport, dstRequisite);
        if (src != null && dst != null && src.getValue() != 0 && src.getValue() >= amount) {
            src.setValue(src.getValue() - amount);
            dst.setValue(dst.getValue() + amount);
            result = true;
        }
        return result;
    }
}
