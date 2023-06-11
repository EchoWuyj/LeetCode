package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 19:40
 * @Version 1.0
 */
public class _06_MergeSorter {
    public static void sort(int[] data) {
        if (data == null || data.length < 2) return;
        int n = data.length;
        int[] tmp = new int[n];
        sort(data, 0, n - 1, tmp);
    }

    public static void sort(int[] data, int left, int right, int[] tmp) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        sort(data, left, mid, tmp);
        sort(data, mid + 1, right, tmp);
        merge1(data, left, mid, right, tmp);
    }

    public static void merge(int[] data, int left, int mid, int right, int[] tmp) {
        int tmpPos = left;
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (data[i] <= data[j]) {
                tmp[tmpPos++] = data[i++];
            } else {
                tmp[tmpPos++] = data[j++];
            }
        }
        while (i <= mid) {
            tmp[tmpPos++] = data[i++];
        }
        while (j <= right) {
            tmp[tmpPos++] = data[j++];
        }
        for (tmpPos = left; tmpPos <= right; tmpPos++) {
            data[left++] = tmp[tmpPos];
        }
    }

    public static void merge1(int[] data, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                data[k] = tmp[j++];
            } else if (j == right + 1) {
                data[k] = tmp[i++];
            } else {
                if (tmp[i] <= tmp[j]) {
                    data[k] = tmp[i++];
                } else {
                    data[k] = tmp[j++];
                }
            }
        }
    }

    public static void sortBU(int[] data) {
        if (data == null || data.length <= 1) return;
        int len = data.length;
        int[] tmp = new int[len];
        for (int size = 1; size < len; size += size) {
            for (int left = 0; left < len - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, len - 1);
                merge(data, left, mid, right, tmp);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
//        sort(data);
//        sortBU(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
