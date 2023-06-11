package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 14:52
 * @Version 1.0
 */
public class _12_RadixSorter {

    // 基数排序：将整数按位数切割成不同的数字(不同的位可以理解成不同的基数)
    // 然后按每个位数分别比较排序(计数排序)从而实现数据最后有序。

    // 基数排序 => 本质上：计数排序
    // 时间复杂度 O(cn) => O(n) C表示最大值的位数，可以省略
    // 空间复杂度 O(n)，不是原地算法 => 创建 output 数组
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 找到最大值  4006869915
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(max, data[i]);
        }

        // 2. 对数组按照每个元素的每位进行计数排序
        // 若 max = 88，则 exp = 100 时， max / exp = 0，结束 for 循环
        // for 循环，循环次数是常量，故基数排序，间复杂度：O(cn) => O(n)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(data, exp); // 时间复杂度：O(n)
        }
    }

    // 核心：计数排序
    // 时间复杂度：O(n+k) k = 10 => O(n)
    private void countSort(int[] data, int exp) {
        // 数字范围 0...9 十个数字，故 int[10]
        int[] count = new int[10];

        for (int i = 0; i < data.length; i++) {

            // 个位数： (234 / 1) % 10 = 4
            // 十位数： (234 / 10) % 10 = 3
            // 百位数： (234 / 100) % 10 = 2

            // 解释说明：十位数 => 取十位数字
            // 1.先 / 10 抹去个位
            // 2.再通过 % 10 将值取出

            int digit = (data[i] / exp) % 10;
            count[digit]++;
        }

        // 计数累加
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // KeyPoint 额外空间，空间复杂度 O(n)，不是原地算法
        int[] output = new int[data.length];

        // 从右往左遍历 => 保证该计数算法是稳定的
        for (int i = data.length - 1; i >= 0; i--) {
            int digit = (data[i] / exp) % 10;
            // count[digit] 元素位置
            // count[digit] - 1 元素索引
            int k = count[digit] - 1;
            // data[i] 表示当前数字
            output[k] = data[i];
            // 处理完一个 digit，计数 count[digit] 减 1
            count[digit]--;
        }

        for (int i = 0; i < data.length; i++)
            data[i] = output[i];
    }

    public static void main(String[] args) {
        int[] data = new int[]{4512, 4231, 31923, 2165, 1141};
        new _12_RadixSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1141, 2165, 4231, 4512, 31923]
    }
}
