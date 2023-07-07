package alg_02_train_dm._09_day_哈希查找;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 14:28
 * @Version 1.0
 */
public class _03_128_longest_consecutive_sequence1 {
        /*
            128 号算法题：最长连续序列
            给定一个 未排序 的整数数组 nums，
            找出数字连续的最长序列（不要求 序列元素 在原数组中连续）的长度。

            输入：nums = [100,4,200,1,3,2]
            输出：4
            解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

            输入：nums = [0,3,7,2,5,8,4,6,0,1]
            输出：9

            提示：
            0 <= nums.length <= 10^4
            -10^9 <= nums[i] <= 10^9

            进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     */

    // KeyPoint 方法一 排序解法 + 直接模拟 => 推荐使用
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n) -> 在数据量很大时，使用到归并排序，会有 O(n) 空间消耗
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;
        Arrays.sort(nums);

        int res = 1;
        int count = 1;
        int n = nums.length;
        // 从第 2 个元素开始遍历，数组索引涉及 nums[i - 1]

        for (int i = 1; i < n; i++) {
            // 前一个和后一个相等情况，不同处理，直接跳过
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else {
                res = Math.max(res, count);
                // 断开计数，count 重新从 1 开始计数
                count = 1;
            }
        }
        // 若数组最后一个元素 nums[n-1] 是连续的，则是没有进入 else 语句中更新 res
        // 故最后返回前，还得执行更新操作 Math.max(res, count)，从而保证更新 res
        return Math.max(res, count);
    }

    // KeyPoint 补充说明：continue 用法
    public int longestConsecutive1(int[] nums) {
        if (nums.length < 2) return nums.length;
        Arrays.sort(nums);
        int res = 1;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 0) {
                // continue' is unnecessary as the last statement in a loop
                // => 就是 for 循环中，if 条件中，continue 是最后执行的代码
                //    使用 continue 来跳出本次循环是没有必要的，可以将 continue 省略
                // continue;
            } else if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 1;
            }
        }
        return Math.max(res, count);
    }
}
