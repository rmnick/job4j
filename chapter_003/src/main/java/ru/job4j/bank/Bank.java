package ru.job4j.bank;

import java.util.*;

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

    public void addAccountToUser(String passport, Account account) {
        Set<User> set = bank.keySet();
        for (User key : set) {
            if (key.getPassport().equals(passport)) {
                bank.get(key).add(account);
                break;
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        Set<User> set = bank.keySet();
        for (User key : set) {
            if (key.getPassport().equals(passport)) {
                bank.get(key).remove(account);
                break;
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> list = new ArrayList<>();
        Set<User> set = bank.keySet();
        for (User key : set) {
            if (key.getPassport().equals(passport)) {
                list = bank.get(key);
                break;
            }
        }
        return list;
    }

    private Account getUserAccount(String passport, String requisite) {
        Account account = null;
        List<Account> list = this.getUserAccounts(passport);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getRequisites().equals(requisite)) {
                    account = list.get(i);
                    break;
                }
            }
        }
        return account;
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
