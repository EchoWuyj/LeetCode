package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:52
 * @Version 1.0
 */
public class _01_BubbleSorter extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length == 1) return;
        int n = data.length;
        for (int round = 1; round <= n; round++) {
            int compareTimes = n - round;
            boolean isSwap = false;
            for (int i = 0; i < compareTimes; i++) {
                if (data[i] > data[i + 1]) {
                    swap(data, i, i + 1);
                    isSwap = true;
                }
            }
            if (!isSwap) break;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _01_BubbleSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
