package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 16:27
 * @Version 1.0
 */
public class IntegerArrayQuickSorter extends Sorter {

    // 使用快排，对存储 Integer 集合 ArrayList 进行排序
    // 区别于：快排对数组排序
    public static void sort(ArrayList<Integer> data) {
        if (data == null || data.size() <= 1) return;
        // KeyPoint 特别注意：
        // 申请 Integer 数组，不是原地排序算法
        Integer[] dataArr = data.toArray(new Integer[data.size()]);
        // 补充说明：
        // 集合转数组，只是引用类型数组，不能是基本数据类型 int
        // Integer[] dataArr = data.toArray(new Integer[data.size()]);

        sort(dataArr, 0, dataArr.length - 1);

        // 将数组 dataArr 复写到 data 集合中
        // 将 data 清空，将重写排序好的元素加入 data 集合
        data.clear();
        // 集合 Arrays API => 将数组转集合
        data.addAll(Arrays.asList(dataArr));
    }

    private static void sort(Integer[] data, int low, int high) {
        if (low >= high) return;
        int j = partition(data, low, high);
        sort(data, low, j - 1);
        sort(data, j + 1, high);
    }

    private static int partition(Integer[] data, int low, int high) {
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
