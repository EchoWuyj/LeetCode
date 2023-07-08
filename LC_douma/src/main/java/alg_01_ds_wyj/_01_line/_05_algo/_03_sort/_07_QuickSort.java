package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 15:33
 * @Version 1.0
 */
public class _07_QuickSort extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        sort(data, 0, n - 1);
    }

    public void sort(int[] data, int low, int high) {
        if (low >= high) return;
        int j = partition(data, low, high);
        sort(data, low, j - 1);
        sort(data, j + 1, high);
    }

    private int partition(int[] data, int low, int high) {

        int pivot = data[high];
        int less = low;
        int great = low;

        for (; great <= high - 1; great++) {
            if (data[great] < pivot) {
                swap(data, great, less);
                less++;
            }
        }
        swap(data, less, high);
        return less;
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        new _07_QuickSort().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
