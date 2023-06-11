package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 16:27
 * @Version 1.0
 */
public class _09_IntegerArrayQuickSorter extends Sorter {
    public void sort(ArrayList<Integer> data) {
        if (data == null || data.size() <= 1) ;
        // KeyPoint 申请 Integer 数组，不是原地排序算法
        Integer[] dataArr = data.toArray(new Integer[data.size()]);
        sort(dataArr, 0, dataArr.length - 1);

        // 将数组 dataArr 复写到 data 集合中
        // 将 data 清空，将重写排序好的元素加入 data 集合
        data.clear();
        for (Integer i : dataArr) data.add(i);
    }

    private void sort(Integer[] data, int low, int high) {
        if (low >= high) return;
        int j = partition(data, low, high);
        sort(data, low, j - 1);
        sort(data, j + 1, high);
    }

    private int partition(Integer[] data, int low, int high) {
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
}
