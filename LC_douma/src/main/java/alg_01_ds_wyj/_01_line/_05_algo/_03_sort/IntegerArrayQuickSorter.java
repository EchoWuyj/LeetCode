package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 11:33
 * @Version 1.0
 */
public class IntegerArrayQuickSorter extends Sorter {
    public static void sort(ArrayList<Integer> data) {
        if (data == null || data.size() <= 1) return;
        int size = data.size();
        Integer[] arr = data.toArray(new Integer[data.size()]);
        sort(arr, 0, size - 1);
        data.clear();
        data.addAll(Arrays.asList(arr));
    }

    public static void sort(Integer[] data, int low, int high) {
        if (low >= high) return;
        int j = partition(data, low, high);
        sort(data, low, j - 1);
        sort(data, j + 1, high);
    }

    public static int partition(Integer[] data, int low, int high) {
        int pivot = data[high];
        int less = low;
        int great = low;

        for (; great <= high - 1; great++) {
            if (data[great] < pivot) {
                swap(data, less, great);
                less++;
            }
        }

        swap(data, less, high);
        return less;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{34, 33, 12, 78, 21, 1, 98, 100};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(data));
        sort(list);
        System.out.println(list);
    }
}
