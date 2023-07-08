package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 19:20
 * @Version 1.0
 */
public class _11_CountingSorter {

    // 计数排序思路：
    // 核心：利用数组 data 索引是有序的，通过将序列中的元素作为索引，其个数作为值放入数组 count ，遍历数组来排序。
    // 时间复杂度是 O(n + k)， 表示数组元素最大范围
    // 注意事项
    // 1.不同的数据规模 n 和 k，并行时间时间复杂度 O(n) 和 O(k)，故时间复杂度 O(n+k)
    // 2.计数排序只能用在数据范围不大的场景中，如果数据范围 k 比排序的数据 n 大很多，就不适合用计数排序了。
    public static void sort(int[] data) {

        // 计数排序操作步骤
        // 1.找到数组中的最大值和最小值，初始化计数器
        // 2.计数
        // 3.计数累加
        // 4.计算每个元素在排序数组中的位置
        // 5.拷贝数组

        // 应用场景
        // 9个人参加比赛，每个人得分都在 0~5分
        // data 2 5 3 0 2 3 0 3 4

        // count
        // index 0 1 2 3 4 5
        // value 2 0 2 3 1 1

        // count(sum)
        // index 0 1 2 3 4 5
        // value 2 2 4 7 8 9

        int n = data.length;
        if (data == null || n <= 1) return;

        // 1.找到数组中的最大值，初始化计数器
        // 考虑负数，使用 min
        int max = data[0];
        int min = data[0];

        for (int i = 1; i < n; i++) { // O(n)
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }

        // 计数数组 count
        // index 表示分数(数组元素)
        // count[index] 表示每个分数出现次数
        // 长度：max-min+1 => index 包括 max 和 min
        int[] count = new int[max - min + 1];

        // 2.计数
        for (int i = 0; i < n; i++) { // O(n)
            // data[i]-min => count 索引
            // 1.减 min，相当于所有元素统一起点，min-min = 0
            // 2.凡是代码涉及 count 位置，都是需要减去 min 的
            count[data[i] - min]++;
        }

        // 3.计数累加
        for (int i = 1; i < count.length; i++) { // O(k)
            // index 数组元素
            // count[index] 元素在数组中最后一个位置
            count[i] += count[i - 1];
        }

        // 4.计算每个元素在排序数组中的位置
        // 创建 output 临时数组 => 空间复杂度 O(n)，不是原地排序算法
        // 存储临时排完序数组元素
        int[] output = new int[n];

        // 注意：这里从后往前遍历 => 保证计数排序是稳定的，否则计数排序就是不稳定
        // 索引 i 从 n-1，每次递减 1
        for (int i = n - 1; i >= 0; i--) { // O(n)
            int num = data[i];
            // num-min => 确定在计数数组 count 索引
            // count[num-min] => 表示 data[i] 在数组 data 最后一个位置
            //                => 因为数组 output 索引从 0 开始，故需要减 1
            //                   即 index = count[num-min]-1
            // 注意：num-min => 统一起点
            int index = count[num - min] - 1;
            output[index] = data[i];
            // 处理完 data[i]，计数数组 count，对应索引位置 (num-min) 上数值减 1
            count[num - min]--;
        }

        // 5.拷贝数组 => output 到 data
        for (int i = 0; i < n; i++) { // O(n)
            data[i] = output[i];
        }

        // KeyPoint 计数排序的特点
        // 1.空间复杂度是 O(n)，不是原地排序算法
        // 2.计数排序是稳定的排序算法
    }

    public static void main(String[] args) {
        int[] data = {4, 2, -2, 8, 3, 3, 1};
        sort(data);
        System.out.println(Arrays.toString(data)); // [-2, 1, 2, 3, 3, 4, 8]
    }
}
