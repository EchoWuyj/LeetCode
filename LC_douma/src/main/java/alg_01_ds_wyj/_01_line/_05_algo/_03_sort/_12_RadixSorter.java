package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 16:53
 * @Version 1.0
 */
public class _12_RadixSorter {
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        int maxValue = data[0];
        for (int num : data) {
            maxValue = Math.max(maxValue, num);
        }

        for (int exp = 1; maxValue / exp > 0; exp *= 10) {
            countSort(data, exp);
        }
    }

    public void countSort(int[] data, int exp) {
        int[] count = new int[10];
        int n = data.length;
        for (int i = 0; i < n; i++) {
            int num = (data[i] / exp) % 10;
            count[num]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = (data[i] / exp) % 10;
            int index = count[num] - 1;
            output[index] = data[i];
            count[num]--;
        }

        for (int i = 0; i < n; i++) {
            data[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{4512, 4231, 31923, 2165, 1141};
        new _12_RadixSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1141, 2165, 4231, 4512, 31923]
    }
}
