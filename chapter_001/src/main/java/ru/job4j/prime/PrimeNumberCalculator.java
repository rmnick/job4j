package ru.job4j.prime;

public class PrimeNumberCalculator {

    public int calc(int finish) {
        int result = 0;
        boolean flag;
        for (int i = 2; i < finish; i++) {
            flag = false;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result++;
            }
        }
        return result;
    }
}
