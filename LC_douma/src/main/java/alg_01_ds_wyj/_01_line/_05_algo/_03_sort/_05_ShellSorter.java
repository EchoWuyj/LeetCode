package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 15:18
 * @Version 1.0
 */
public class _05_ShellSorter extends Sorter {

    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;
        do {
            h = ((int) (Math.pow(3, k) - 1)) / 2;
            if (n >= 3 && h > n / 3) break;
            list.add(h);
            k++;
        } while (h <= n / 3);

        for (int element = list.size() - 1; element >= 0; element--) {
            h = list.get(element);
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (data[j] < data[j - h]) {
                        swap(data,j,j-h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void sort1(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        swap(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _05_ShellSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}
