package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 21:11
 * @Version 1.0
 */
public class _08_ThreeWayQuickSorter extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length == 1) return;
        sort(data, 0, data.length - 1);
    }

    public void sort(int[] data, int low, int high) {
        if (low >= high) return;

        // int random = new Random().nextInt(high - low + 1) + low;
        // swap(data, random, high);

        int pivot = data[high];
        int less = low;
        int greater = high;
        int i = less;

        while (i <= greater) {
            if (data[i] < pivot) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                swap(data, i, greater);
                greater--;
            } else {
                i++;
            }
        }

        sort(data, low, less - 1);
        sort(data, less + 1, high);
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        new _08_ThreeWayQuickSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
