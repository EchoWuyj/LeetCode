package alg_01_新手班_wyj.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-09-06 19:14
 * @Version 1.0
 */
public class Code04_SelectionSort {
    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }

    public static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] =temp;
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < length; j++) {
                minValueIndex = (arr[j] < arr[minValueIndex]) ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
