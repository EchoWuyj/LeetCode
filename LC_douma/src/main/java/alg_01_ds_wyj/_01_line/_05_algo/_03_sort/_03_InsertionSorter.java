package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 11:53
 * @Version 1.0
 */
public class _03_InsertionSorter extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public void sort1(int[] data) {
        int n = data.length;
        if (data == null || data.length <= 1) return;
        for (int i = 1; i < n; i++) {
            int tmp = data[i];
            int j;
            for (j = i; j > 0; j--) {
                if (tmp < data[j - 1]) {
                    data[j] = data[j - 1];
                } else {
                    break;
                }
            }
            data[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _03_InsertionSorter().sort1(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
