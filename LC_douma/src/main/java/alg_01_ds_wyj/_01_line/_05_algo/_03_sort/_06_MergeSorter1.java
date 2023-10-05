package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 19:40
 * @Version 1.0
 */
public class _06_MergeSorter1 {
    public static void sort(int[] data) {
        if (data == null || data.length == 1) return;
        int[] tmp = new int[data.length];
        sort(data, 0, data.length - 1, tmp);
    }

    public static void sort(int[] data, int left, int right, int[] tmp) {
        if (left == right) return;
        int mid = (left + right) / 2;
        sort(data, left, mid, tmp);
        sort(data, mid + 1, right, tmp);
        merge(data, left, mid, right, tmp);
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
            } else {
                if (tmp[i] <= tmp[j]) {
                    data[index] = tmp[i++];
                } else {
                    data[index] = tmp[j++];
                }
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
