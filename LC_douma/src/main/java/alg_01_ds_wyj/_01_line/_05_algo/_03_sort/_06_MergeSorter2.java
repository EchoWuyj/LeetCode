package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-07 21:32
 * @Version 1.0
 */
public class _06_MergeSorter2 {

    public static void sortBottomUp(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        int[] tmp = new int[n];

        for (int size = 1; size < n; size += size) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min((left + 2 * size - 1), n - 1);
                merge(data, left, mid, right, tmp);
            }
        }
    }

    public static void merge(int[] data, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int i = left;
        int j = mid + 1;

        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                data[index] = tmp[j++];
            } else if (j == right + 1) {
                data[index] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[index] = tmp[i++];
            } else {
                data[index] = tmp[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        sortBottomUp(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
