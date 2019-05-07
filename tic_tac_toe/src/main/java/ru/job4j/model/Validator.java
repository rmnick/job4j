package ru.job4j.model;

import ru.job4j.controller.IInput;
import ru.job4j.view.IOut;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    private Map<String, Integer> map = new HashMap<>();
    public static final String KEY_START = "start game";
    public static final String KEY_PLAYER_ONE = "player one";
    public static final String KEY_PLAYER_TWO = "player two";
    public static final String KEY_SIZE = "size option";
    public static final String KEY_HELP = "help";
    private Board board;
    private IOut out;
    private IInput input;

    public Validator(final Board board, IOut out, IInput input) {
        this.board = board;
        this.out = out;
        this.input = input;
        init();
    }

    public void init() {
        map.put(KEY_START, 1);
        map.put(KEY_PLAYER_ONE, 1);
        map.put(KEY_PLAYER_TWO, 1);
        map.put(KEY_SIZE, 1);
        map.put(KEY_HELP, 1);
    }

    public int[] parseMove(String str) {
        while (!validateMove(str)) {
            out.printAlert("enter correct coordinate: ");
            str = input.input();
        }
        int[] arr = new int[2];
        arr[0] = Integer.valueOf(str.substring(0, 1));
        arr[1] = Integer.valueOf(str.substring(1));
        return arr;
    }

    public boolean validateMove(String str) {
        boolean result = false;
        int size = board.getBoard().length - 1;
        str = str.trim();
        if (str.matches(String.format("[0-%d][0-%d]", size, size))) {
            result = true;
        }
        return result;
    }

    public boolean validateItem(String str) {
        boolean result = false;
        if (map.get(str) != null) {
            result = true;
        }
        return result;
    }

    public boolean validateSize(String str) {
        boolean result = false;
        if (str.matches("[0-9]")) {
            result = true;
        }
        return result;
    }
}
