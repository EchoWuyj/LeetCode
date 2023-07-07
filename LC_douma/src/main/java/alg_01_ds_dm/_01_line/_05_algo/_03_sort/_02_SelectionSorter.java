package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:30
 * @Version 1.0
 */

public class _02_SelectionSorter extends Sorter {

    // 选择排序
    // 思路：不断在剩余数组元素中，选择最小元素，与数组没有排序元素交换
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        // 注意：data.length <= 1 而不是 data.length == 1
        if (data == null || data.length <= 1) return;
        int n = data.length;
        // 控制选择排序的轮数：几个元素 => 几轮选择
        // i 从 0 开始，故 i < n
        for (int i = 0; i < n; i++) {
            // i 从 0 开始，依次从小到大，选择数组元素
            // 找到 [i, n) 中的最小元素所在的索引，将其赋值给 minNumIndex
            int minNumIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[minNumIndex]) {
                    minNumIndex = j;
                }
            }
            // 只有一轮比较结束之后，才能确定 minNumIndex，将 [minNumIndex] 和 [i] 进行交换
            swap(data, i, minNumIndex);

            // 注意事项
            // 1.涉及前后元素交换，可能影响相同元素的相对次序，选择排序是不稳定排序算法
            //   原始 5 8 5 2 9
            //       红  黑
            //   一轮 2 8 5  5 9
            //           黑 红
            // 2.使用场景很少的，一般不使用选择排序 => 记忆：一般不做选择
            // 3.原地排序算法
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _02_SelectionSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
