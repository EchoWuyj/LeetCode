package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 19:20
 * @Version 1.0
 */
public class _11_CountingSorter {

    // 计数排序
    // 思路：
    //
    public static void sort(int[] data) {
        int length = data.length;
        if (data == null || length <= 1) return;

        // 1. 找到数组中的最大值，初始化计数器
        // KeyPoint 考虑负数，使用 min
        int max = data[0];
        int min = data[0];

        for (int i = 1; i < length; i++) { // O(n)
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }
        int[] count = new int[max - min + 1];

        // 2. 计数
        for (int i = 0; i < length; i++) { // O(n)
            // data[i]-min => count 索引
            // KeyPoint 减 min，相当于所有元素统一起点，min-min = 0
            //          凡是代码涉及 count 位置，都是需要减去 min 的
            count[data[i] - min]++;
        }

        // 3. 计数累加
        // KeyPoint 不同的数据规模 n 和 k，并行时间时间复杂度 O(n) 和 O(k)，故时间复杂度 O(n+k)
        for (int i = 1; i < count.length; i++) { // O(k)
            count[i] += count[i - 1];
        }

        // 4. 计算每个元素在排序数组中的位置
        // KeyPoint 创建 output 临时数组 => 空间复杂度 O(n)，不是原地排序算法
        int[] output = new int[length];

        // 注意：这里从后往前遍历
        for (int i = length - 1; i >= 0; i--) { // O(n)
            int j = data[i];
            // count 存储是 data 元素的个数 num => count[j - min]
            // 对应 output 索引位置 num-1，数组索引从0 开始 => count[j - min] - 1
            int k = count[j - min] - 1;
            output[k] = data[i];

            // count 数组，j-min 索引，再减 1
            count[j - min]--;
        }

        // 5. 拷贝数组
        for (int i = 0; i < length; i++) { // O(n)
            data[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] data = {4, 2, -2, 8, 3, 3, 1};
        sort(data);
        System.out.println(Arrays.toString(data)); // [-2, 1, 2, 3, 3, 4, 8]
    }
}
