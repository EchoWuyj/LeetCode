package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 14:52
 * @Version 1.0
 */
public class _12_RadixSorter {

    // 基数排序思路：
    // 1.将整数按位数切割成不同的数字(不同的位可以理解成不同的基数)
    // 2.然后按每个位数分别比较排序(计数排序)从而实现数据最后有序

    // 基数排序 => 本质上：计数排序
    // 时间复杂度 O(cn) => O(n) C表示最大值的位数，可以省略
    // 空间复杂度 O(n)，不是原地算法 => 创建 output 数组
    public void sort(int[] data) {

        // 基数排序操作步骤
        // 1.找到最大值 4006869915
        // 2.对数组按照每个元素的每位进行计数排序
        // 3.

        // 应用场景：
        // 手机号 15928782344，18722223423，13423453212，14567865397 ...
        // 将手机号从小到大进行排序
        // 因为数据范围很大，不适合使用桶和计数排序

        //       第一位排序  第二位排序  第三位排序  第四位排序
        // 4512   423|1      45|1|2     1|1|41    |1|141
        // 4231   114|1      19|2|3     2|1|65    |1|923
        // 1923   451|2      42|3|1     4|2|31    |2|165
        // 2165   192|3      11|4|1     4|5|12    |4|231
        // 1141   216|5      21|6|5     1|9|23    |4|512

        // 1.必须是稳定排序算法
        //   => 每一位上，数字值相同，保证数据相对次数不能改变
        // 2.数据每位数据范围不大 0~9
        // 3.必须是线性时间复杂度 O(n) 算法
        // => 计数排序

        if (data == null || data.length <= 1) return;

        // 1.找到最大值
        int max = data[0];

        for (int num : data) {
            max = Math.max(num, max);
        }

        // 2.对数组按照每个元素的每位进行计数排序
        // 1.若 max = 88，则 exp = 100 时， max / exp = 0，结束 for 循环
        // 2.for 循环，循环次数是常量，故基数排序，时间复杂度：O(cn) => O(n)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(data, exp); // 时间复杂度：O(n)
        }

        // KeyPoint
        // 基数排序的特点
        // 1.时间复杂度：O(Cn)，C 表示最大值的位数，可以省略
        // 2.基数排序对要排序数据是有要求
        //   需要可以分割出来独立的“位”来比较，而且每一位的数据范围不能太大(方便计数排序实现)
        //   要可以用线性排序算法来排序，否则基数排序的时间复杂度就无法做到 O(n)了
    }

    // KeyPoint 基数排序核心：计数排序
    // 时间复杂度：O(n+k) k = 10 => O(n)
    // 形参 exp 控制 data 的不同位数
    private void countSort(int[] data, int exp) {
        // 数字范围 0...9 十个数字，故 int[10]
        int[] count = new int[10];
        int n = data.length;
        // 计数
        for (int i = 0; i < n; i++) {
            // KeyPoint 区别： % 和 &
            // bug：将 % 写成了 &，从而导致 ArrayIndexOutOfBoundsException
            int num = (data[i] / exp) % 10;
            count[num]++;

            // 个位数：(234 / 1) % 10 = 4
            // 十位数：(234 / 10) % 10 = 3
            // 百位数：(234 / 100) % 10 = 2
            // 解释说明：十位数 => 取十位数字
            // 1.先 / 10 抹去个位
            // 2.再通过 % 10 将值取出
        }

        // 计数累加
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 额外空间，空间复杂度 O(n)，不是原地算法
        int[] output = new int[n];

        // 从右往左遍历 => 保证该计数算法是稳定的
        for (int i = n - 1; i >= 0; i--) {
            int num = (data[i] / exp) % 10;
            // count[num] 元素在数组中最后一个位置
            // count[num]-1 元素在 output 数组索引位置
            int index = count[num] - 1;
            // data[i] 表示当前数字
            output[index] = data[i];
            // 处理完一个 num，计数 count[num] 减 1
            count[num]--;
        }

        // 拷贝
        for (int i = 0; i < n; i++)
            data[i] = output[i];
    }

    public static void main(String[] args) {
        int[] data = new int[]{4512, 4231, 31923, 2165, 1141};
        new _12_RadixSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1141, 2165, 4231, 4512, 31923]
    }
}
