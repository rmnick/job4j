package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class User {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport == user.passport
                && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return  new StringJoiner("; ", "(", ")")
                .add(name)
                .add(passport)
                .toString();
    }
}
