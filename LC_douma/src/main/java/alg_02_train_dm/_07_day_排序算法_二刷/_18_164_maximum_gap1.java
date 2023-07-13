package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:04
 * @Version 1.0
 */
public class _18_164_maximum_gap1 {

    /*
        164. 最大间距
        给定一个 无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。
        如果数组元素个数小于 2，则返回 0 。
        您必须编写一个在「线性时间 O(n)」内运行并使用「线性额外空间 O(n)」的算法。

        示例 1:
        输入: nums = [3,6,9,1]
        输出: 3
        解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。

        示例 2:
        输入: nums = [10]
        输出: 0
        解释: 数组元素个数小于 2，因此返回 0。

        提示：
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^9

     */

    // KeyPoint 方法一 Java 内置排序
    // 时间复杂度 O(nlogn) => 不满足题目 O(n) 要求
    public int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        // 默认升序排列 O(nlogn)
        Arrays.sort(nums);

        // 最大间距
        int maxGap = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        return maxGap;
    }

    // KeyPoint 线性时间复杂度 => 桶排序，计数排序，基数排序 => 再根据题目数据范围，从而确定使用什么算法
    // 0 <= nums[i] <= 10^9 => 计数排序 × => 数据范围太大
    // 0 <= nums[i] <= 10^9 => 数据很大 => 基数排序 √ => 处理电话号码
    // 1.将整数按位数切割成不同的数字 => 不同的位可以理解成不同的基数
    // 2.然后按每个位数分别比较排序(计数排序)，从而实现数据最后有序

    // KeyPoint 方法二：手动实现排序 => 基数排序 => 需要掌握
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        // 基数排序
        radixSort(nums);

        // 最大间距计算
        int maxGap = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }
        return maxGap;
    }

    // KeyPoint 基数排序 => 电话号码形式数据
    public void radixSort(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // 1.找到最大值
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        // 2.对数组按照每个元素的每位进行计数排序
        // KeyPoint 注意：for 循环条件 不是 max / exp < 0
        // max / exp > 0 => for 循环执行，当 max / exp = 0 循环停止
        // KeyPoint debug 经验
        // 1.优先通过 System.out.println 打印输出方式，缩小 bug 位置
        // 2.实在不行再去 debug，因为 debug 很麻烦
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(nums, exp);
        }
    }

    // KeyPoint 计数排序
    // 应用场景：比赛评分，0-10 分 => 数据范围不大场景
    public void countSort(int[] nums, int exp) {
        int[] count = new int[10];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 获取某一位上的数字
            int num = (nums[i] / exp) % 10;
            count[num]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            // 获取某一位上的数字
            int num = (nums[i] / exp) % 10;
            int index = count[num] - 1;
            // 一个整体数字 nums[i] 赋值给 output[i]
            output[index] = nums[i];
            count[num]--;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = output[i];
        }
    }
}
