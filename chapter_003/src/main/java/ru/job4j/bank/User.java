package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private int passport;
    //private List<Account> list;

    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
        //this.list = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passport == user.passport &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

    public String getName() {
        return name;
    }
}
