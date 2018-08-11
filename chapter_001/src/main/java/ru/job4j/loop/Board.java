package ru.job4j.loop;
/**
 * Board.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/11.
 */
public class Board {
    public String paint(int width, int height) {
      StringBuilder screen = new StringBuilder();
      String ln = System.lineSeparator();
      for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
              if ((i + j) % 2 == 0) {
                  screen.append("x");
              } else {
                  screen.append(" ");
              }
          }
          screen.append(ln);
      }
      return screen.toString();
    }

}
