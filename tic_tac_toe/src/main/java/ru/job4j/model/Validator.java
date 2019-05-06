package ru.job4j.model;

import ru.job4j.controller.IInput;
import ru.job4j.view.IOut;

public class Validator {
    private Board board;
    private IOut out;
    private IInput input;

    public Validator(final Board board, IOut out, IInput input) {
        this.board = board;
        this.out = out;
        this.input = input;
    }

    public int[] parseMove(String str) {
        while (!validateMove(str)) {
            out.printAlert("enter correct coordinate");
            str = input.input();
        }
        int[] arr = new int[2];
        arr[0] = Integer.valueOf(str.substring(0, 1));
        arr[1] = Integer.valueOf(str.substring(1));
        return arr;
    }

    public boolean validateMove(String str) {
        boolean result = false;
        int size = board.board.length - 1;
        str = str.trim();
        if (str.matches(String.format("[0-%d][0-%d]", size, size))) {
            result = true;
        }
        return result;
    }
}
