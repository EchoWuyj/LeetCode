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

    // 三路快排(三路切分) => 将数组分成 3 段
    private void sort(int[] data, int low, int high) {

        // 1.分区过程中 => 不变式
        // [low,1ess) => 数组元素 < pivot
        // [less,i) => 数组元素 = pivot
        // [i,great] 未处理 数组元素
        // (great,high] => 数组元素 > pivot

        // 2.分区完后，得到 3 个区间
        // 数组元素 < pivot
        // 数组元素 = pivot
        // 数组元素 > pivot

        // 3.之后再去对 < pivot 区域 和 > pivot 区域进行分区排序

        // KeyPoint 详细过程
        // 一、变量定义
        // pivot，less，great，i

        // 二、比较逻辑
        // 1.若 data[i] < pivot，将 data[i] 往前放，故和 less 交换，即 swap(data, i, less)
        //   less 指针右移，从而保证 [low,1ess) < pivot
        //   同时，当前元素 [i] 已经处理完，故右移 i 指针
        // 2.若 data[i] > pivot，将 data[i] 往后放，故和 great 交换，即 swap(data, i, great)
        //   great 指针左移，从而保证 (great,high] > pivot
        //   和 great 交换后，[great] 还没有处理，i 不能右移，i 不用变化

        //  i
        //  ↓
        //  46 9 34 11 33 20 22 20 45 8 10 20
        //  ↑                               ↑
        //  low                            high
        //  less                           great

        //                    i
        //                    ↓
        // 9 10 11 8 20 20 20 45 22 33 34 46
        // ↑         ↑     ↑               ↑
        // low     less   great           high

        if (low >= high) return;

        // KeyPoint 1.变量定义
        int pivot = data[high];
        int less = low;
        int great = high;
        // i 遍历 data 数组指针
        int i = low;

        // KeyPoint 2.分区逻辑 => 在递归函数中
        // i > great，说明 data 中每个元素都已经遍历过了
        while (i <= great) {
            // 升序排列，若想降序排列，交换 < 和 > 即可
            if (data[i] < pivot) {
                // 将 data[i] 往前放，故和 less 交换
                // 从而保证 [low,1ess) < pivot
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                // 将 data[i] 往后放，故和 great 交换
                // 从而保证 (great,high] > pivot
                swap(data, i, great);
                great--;
                // 和 great 交换后，[great] 还没有处理，i 不能右移，i 不用变化
            } else {
                // data[i] == pivot
                i++;
            }
        }

        // [less,great] 区间和 pivot 相等，不用排序
        // 下次子数组排序，直接从 [low,less-1] 和 [great+1,high] 开始即可
        sort(data, low, less - 1);
        sort(data, great + 1, high);
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        new _08_ThreeWayQuickSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
