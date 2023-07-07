package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 19:40
 * @Version 1.0
 */
public class _06_MergeSorter1 {
    public static void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        int[] tmp = new int[n];
        sort(data, 0, n - 1, tmp);
    }

    public static void sort(int[] data, int left, int right, int[] tmp) {
        if (left == right) return;
        int mid = (left + right) / 2;
        sort(data, left, mid, tmp);
        sort(data, mid + 1, right, tmp);
        merge2(data, left, right, mid, tmp);
    }

    public static void merge(int[] data, int left, int right, int mid, int[] tmp) {
        int index = left;
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (data[i] <= data[j]) {
                tmp[index++] = data[i++];
            } else {
                tmp[index++] = data[j++];
            }
        }

        while (i <= mid) {
            tmp[index++] = data[i++];
        }

        while (j <= right) {
            tmp[index++] = data[j++];
        }

        for (index = left; index <= right; index++) {
            data[left++] = tmp[index];
        }
    }

    public static void merge2(int[] data, int left, int right, int mid, int[] tmp) {
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
        sort(data);
//        sortBU(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
