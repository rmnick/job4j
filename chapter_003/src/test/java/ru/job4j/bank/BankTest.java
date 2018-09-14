package ru.job4j.bank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void testDeleteUser() {
        User serg = new User("Sergey", "1234");
        User vlad = new User("Vlad", "4567");
        Bank bank = new Bank();
        bank.addUser(serg);
        bank.addUser(vlad);
        bank.deleteUser(serg);
        assertThat(bank.getUsers().toString(), is("[(Vlad; 4567)]"));
    }
    @Test
    public void testDeleteAccount() {
        User serg = new User("Sergey", "1234");
        Account acc1 = new Account(245, "11111");
        Account acc2 = new Account(100, "22222");
        Account acc3 = new Account(0, "33333");
        Bank bank = new Bank();
        bank.addUser(serg);
        bank.addAccountToUser(serg.getPassport(), acc1);
        bank.addAccountToUser(serg.getPassport(), acc2);
        bank.addAccountToUser(serg.getPassport(), acc3);
        bank.deleteAccountFromUser(serg.getPassport(), acc2);
        assertThat(bank.getUserAccounts(serg.getPassport()).toString(), is("[(245.0; 11111), (0.0; 33333)]"));
    }
    @Test
    public void testTransferBetweenAccounts() {
        User serg = new User("Sergey", "1234");
        User vlad = new User("Vlad", "4567");
        Account acc1 = new Account(245, "11111");
        Account acc2 = new Account(100, "22222");
        Bank bank = new Bank();
        bank.addUser(serg);
        bank.addUser(vlad);
        bank.addAccountToUser(serg.getPassport(), acc1);
        bank.addAccountToUser(vlad.getPassport(), acc2);
        bank.transferMoney(serg.getPassport(), acc1.getRequisites(), vlad.getPassport(), acc2.getRequisites(), 50);
        assertThat(acc1.getValue(), is(195.0));
    }
}
