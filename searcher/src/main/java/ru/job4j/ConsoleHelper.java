package ru.job4j;

public class ConsoleHelper implements IHelper {
    @Override
    public void help() {
        System.out.println(new StringBuilder()
                .append("help: ")
                .append(System.lineSeparator())
                .append("-d \"absolute path\" -n \"filename(regular expression or mask)\" "
                        + "-m,r,f(mask, regular expression, full name) -o \"log file absolute path\"")
                .append(System.lineSeparator()));
    }
}
