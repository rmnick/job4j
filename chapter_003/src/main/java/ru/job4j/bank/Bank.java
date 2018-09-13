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

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        List<Account> listOne = new ArrayList<>();
        List<Account> listTwo = new ArrayList<>();
        Set<User> set = bank.keySet();
        Account src = null;
        Account dst = null;
        for (User key : set) {
            if (key.getPassport().equals(srcPassport)) {
                listOne = bank.get(key);
                break;
            }
        }
        for (User key : set) {
            if (key.getPassport().equals(dstPassport)) {
                listTwo = bank.get(key);
                break;
            }
        }
        for (int i = 0; i < listOne.size(); i++) {
            if (listOne.get(i).getRequisites().equals(srcRequisite)) {
                src = listOne.get(i);
                break;
            }
        }
        for (int i = 0; i < listTwo.size(); i++) {
            if (listTwo.get(i).getRequisites().equals(dstRequisite)) {
                dst = listTwo.get(i);
                break;
            }
        }
        if (src != null && dst != null && src.getValue() != 0 && src.getValue() >= amount) {
            src.setValue(src.getValue() - amount);
            dst.setValue(dst.getValue() + amount);
            result = true;
        }
        return result;
    }
}
