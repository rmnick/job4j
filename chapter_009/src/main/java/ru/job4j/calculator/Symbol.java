package ru.job4j.calculator;

public enum Symbol {
    ADD("+"), SUB("-"), DIV("/"), MUL("*"), LOG("log"), SIN("sin"),
    COS("cos"), TAN("tan"), OFF("off"), CE("c");

    public final String name;

    Symbol(String name) {
        this.name = name;
    }
}
