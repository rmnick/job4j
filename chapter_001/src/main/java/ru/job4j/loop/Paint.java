package ru.job4j.loop;
/**
 * The Paint.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/10.
 */
public class Paint {
    public String piramid(int height) {
        StringBuilder screen = new StringBuilder();
        int width = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
