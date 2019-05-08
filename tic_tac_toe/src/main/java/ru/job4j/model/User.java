package ru.job4j.model;

import ru.job4j.controller.IInput;
import ru.job4j.view.IOut;


public class User implements IPlayer {
    private final Validator validator;
    private final IOut out;
    private final IInput input;
    private final Logic logic;
    private final boolean mark;



    public User(final Validator validator, final IInput input, final IOut out, final Logic logic, final boolean mark) {
        this.validator = validator;
        this.input = input;
        this.out = out;
        this.logic = logic;
        this.mark = mark;
    }

    public void move() {
        String mv = this.input.input();
        while (!logic.move(new Figure(this.mark), validator.parseMove(mv)[0], validator.parseMove(mv)[1])) {
            out.printAlert("the cell is busy");
            mv = this.input.input();
        }
    }
}
