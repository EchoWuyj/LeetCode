package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 15:16
 * @Version 1.0
 */
public class _11_CountingSorter {
    public static void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        int max = data[0];
        int min = data[0];
        for (int num : data) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        int[] count = new int[max - min + 1];

        for (int i = 0; i < n; i++) {
            count[data[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = data[i];
            int index = count[num - min] - 1;
            output[index] = data[i];
            count[num - min]--;
        }

        for (int i = 0; i < n; i++) {
            data[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] data = {4, 2, -2, 8, 3, 3, 1};
        sort(data);
        System.out.println(Arrays.toString(data)); // [-2, 1, 2, 3, 3, 4, 8]
    }
}
