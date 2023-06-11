package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:30
 * @Version 1.0
 */

public class _02_SelectionSorter extends Sorter {

    // 思路：不断在剩余数组元素中，选择最小元素，与数组没有排序元素交换
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        // 控制选择排序的轮数
        // 几个元素 => 几轮选择
        for (int i = 0; i < data.length; i++) {
            // 找到 [i, n) 中的最小元素所在的索引
            int minNumIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minNumIndex]) {
                    minNumIndex = j;
                }
            }
            // 只有一轮比较结束之后，才能确定 minNumIndex，将 [minNumIndex] 和 [i] 进行交换
            swap(data, i, minNumIndex);

            // 选择排序是不稳定排序算法
            // => 涉及前后元素交换，可能影响相同元素的相对次序
            // => 使用场景很少的，一般不使用选择排序
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _02_SelectionSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
