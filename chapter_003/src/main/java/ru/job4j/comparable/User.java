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

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + String.format("(%d)", this.age);
    }

    @Override
    public int compareTo(User o) {
        return Integer.valueOf(this.age).compareTo(Integer.valueOf(o.getAge()));
    }
}
