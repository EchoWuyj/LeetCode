package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 11:33
 * @Version 1.0
 */
public class _09_IntegerArrayQuickSorter extends Sorter {
    public static void sort(ArrayList<Integer> data) {
        if (data == null || data.size() <= 1) return;
        Integer[] dataArr = data.toArray(new Integer[data.size()]);
        sort(dataArr, 0, dataArr.length - 1);

        data.clear();
        for (Integer dataEle : dataArr) data.add(dataEle);
    }

    public static void sort(Integer[] data, int low, int high) {
        if (low >= high) return;
        int j = partition(data, low, high);
        sort(data, low, j - 1);
        sort(data, j + 1, high);
    }

    public static int partition(Integer[] data, int low, int high) {
        int less = low;
        int great = low;
        int pivot = data[high];
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
        ArrayList<Integer> list = new ArrayList<>();
        Integer[] data = new Integer[]{34, 33, 12, 78, 21, 1, 98, 100};
        for (Integer dataEle : data) {
            list.add(dataEle);
        }
        sort(list);
        System.out.println(list);
    }
}
