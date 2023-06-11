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
        int max = data[0];
        int len = data.length;
        for (int i = 1; i < len; i++) {
            max = Math.max(max, data[i]);
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(data, exp);
        }
    }

    private void countSort(int[] data, int exp) {
        int[] count = new int[10];
        for (int i = 0; i < data.length; i++) {
            int digit = (data[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            int digit = (data[i] / exp) % 10;
            int k = count[digit] - 1;
            output[k] = data[i];
            count[digit]--;
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{4512, 4231, 31923, 2165, 1141};
        new _12_RadixSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1141, 2165, 4231, 4512, 31923]
    }
}
