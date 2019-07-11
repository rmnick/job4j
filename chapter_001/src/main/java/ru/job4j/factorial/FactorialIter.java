package ru.job4j.factorial;

public class FactorialIter implements IFactorial {

    @Override
    public int calc(int num) {
        int result = 1;
        if (num != 0) {
            for (int i = 1; i <= num; i++) {
                result = result * i;
            }
        }
        return result;
    }
}
