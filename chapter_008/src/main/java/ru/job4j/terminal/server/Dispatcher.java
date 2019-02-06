package ru.job4j.terminal.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Dispatcher {
    private final String SHOW_ALL = "ls";
    private final String MOVE_UP = "cd..";
    private final String MOVE_IN = "cd";
    private final Map<String, BiFunction<String, DataInputStream, DataOutputStream>> tempalte = new HashMap<>();

}
