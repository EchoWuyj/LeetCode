package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 21:51
 * @Version 1.0
 */
public class _01_BubbleSorter extends Sorter {

    // 冒泡排序
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        int n = data.length;
        if (data == null || n <= 1) return;
        // 控制冒泡轮数：几个元素 => 几轮冒泡
        // round <= n 或者 round < n，两种形式都可以
        for (int round = 1; round < n; round++) {
            // 标识是否交换，默认是 false，只有交换之后，才设置为 true
            // 减少比较次数
            boolean hasSwap = false;
            // 最开始，若数组有 6 个元素，则第一轮需要比较 5 (6-1) 次
            int compareTimes = n - round;
            // 控制每轮比较次数
            // => 每轮排序后，数组最大值确定，从而通过 compareTimes 值控制数组索引上限
            for (int i = 0; i < compareTimes; i++) {
                // 只有严格大于，才进行交换，故冒泡排序是稳定的
                // compareTimes 最大值为 5，i 最大值为 4，i+1 不会越界
                if (data[i] > data[i + 1]) {
                    swap(data, i, i + 1);
                    hasSwap = true;
                }
            }
            // 在 round 一轮结束之后，再去判断 hasSwap
            // 如果没有交换，则数组已经排好序，直接 break
            // !hasSwap 整体为 true，则 hasSwap 为 false
            if (!hasSwap) break;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _01_BubbleSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
