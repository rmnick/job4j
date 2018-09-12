package ru.job4j.comparable;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(User o) {
        int result = 0;
        if (this.age > o.getAge()) {
            result = 1;
        }
        if (this.age < o.getAge()) {
            result = -1;
        }
        return result;
    }
}
