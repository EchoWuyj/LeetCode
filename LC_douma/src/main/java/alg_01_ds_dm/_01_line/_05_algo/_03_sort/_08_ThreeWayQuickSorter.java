package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 14:56
 * @Version 1.0
 */
public class _08_ThreeWayQuickSorter extends Sorter {
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    private void sort(int[] data, int low, int high) {
        if (low >= high) return;

        // 分区点
        int pivot = data[high];
        int less = low;
        int great = high;

        // KeyPoint 不变式
        // [low,1ess) < pivot
        // [less,i) = pivot
        // [i,great] 未处理
        // (great,high] > pivot

        // i 遍历 data 数组指针
        int i = low;
        // i > great，说明 data 中每个元素都已经遍历过了
        while (i <= great) {
            // KeyPoint 升序排列，若想降序排列，交换 < 和 > 即可
            if (data[i] < pivot) {
                // 将 data[i] 往前放，故和 less 交换
                // 保证 [low,1ess) < pivot
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                // 将 data[i] 往后放，故和 great 交换
                swap(data, i, great);
                great--;
                // 和 great 交换后，[great] 还没有处理，i 不能右移，i 不用变化
            } else {
                // data[i] == pivot
                i++;
            }
        }

        // [less,great] 区间和 pivot 相等，不用排序，直接从 less-1 和 great+1 开始处理
        sort(data, low, less - 1);
        sort(data, great + 1, high);
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        new _08_ThreeWayQuickSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
