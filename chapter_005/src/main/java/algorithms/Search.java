package algorithms;

public class Search {

    public static boolean binarySearch(int value, int[] arr) {
        int pointer;
        int head = 0;
        int tail = arr.length - 1;
        while (true) {
            if (tail < head) {
                return false;
            }
            pointer = (head + tail) / 2;
            if (arr[pointer] == value) {
                return true;
            }
            if (arr[pointer] > value) {
                tail = pointer - 1;
            } else {
                head = pointer + 1;
            }
        }
    }


}
