package algorithms;

public class Sort {

    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                 temp = arr[j];
                 arr[j] = arr[j + 1];
                 arr[j + 1] = temp;
                }
            }
        }
    }

    public static void choiceSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[j + 1] < arr[i]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}
