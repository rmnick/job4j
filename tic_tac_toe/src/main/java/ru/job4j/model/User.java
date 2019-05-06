package ru.job4j.model;

import ru.job4j.controller.IInput;
import ru.job4j.view.IOut;


public class User implements IPlayer {
    public final Validator validator;
    public final IOut out;
    public final IInput input;
    public final Logic logic;


    public User(final Validator validator, final IInput input, final IOut out, final Logic logic) {
        this.validator = validator;
        this.input = input;
        this.out = out;
        this.logic = logic;
    }

    public void move() {
        String mv = this.input.input();
        while (!logic.move(new Figure(true), validator.parseMove(mv)[0], validator.parseMove(mv)[1])) {
            out.printAlert("the cell is busy");
            mv = this.input.input();
        }
    }
}
