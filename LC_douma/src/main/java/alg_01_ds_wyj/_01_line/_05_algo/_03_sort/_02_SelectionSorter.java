package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 11:03
 * @Version 1.0
 */
public class _02_SelectionSorter extends Sorter {

    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        for (int i = 0; i < n; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            swap(data, i, minValueIndex);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _02_SelectionSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
