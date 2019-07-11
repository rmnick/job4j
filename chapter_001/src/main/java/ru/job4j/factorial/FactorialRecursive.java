package ru.job4j.factorial;

public class FactorialRecursive implements IFactorial {

    @Override
    public int calc(int num) {
        if (num == 0) {
            return 1;
        }
        return num * calc(num - 1);
    }
}
