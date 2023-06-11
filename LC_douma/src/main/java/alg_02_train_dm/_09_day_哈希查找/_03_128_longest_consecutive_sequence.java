package alg_02_train_dm._09_day_哈希查找;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 14:28
 * @Version 1.0
 */
public class _03_128_longest_consecutive_sequence {
        /*
            leetcode 128 号算法题：最长连续序列
            给定一个未排序的整数数组 nums ，
            找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

            输入：nums = [100,4,200,1,3,2]
            输出：4

            输入：nums = [0,3,7,2,5,8,4,6,0,1]
            输出：9

            0 <= nums.length <= 10^4
            -10^9 <= nums[i] <= 10^9

            进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     */

    // KeyPoint 方法一 排序解法
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n) -> 在数据量很大时，使用到归并排序，会有 O(n) 空间消耗
    public int longestConsecutive1(int[] nums) {
        if (nums.length < 2) return nums.length;

        Arrays.sort(nums);

        int ans = 1;
        int count = 1;
        // 从第 2 个元素开始遍历，数组索引涉及 nums[i - 1]
        for (int i = 1; i < nums.length; i++) {
            // 前一个和后一个相等情况，不同处理，直接跳过
            if (nums[i] == nums[i - 1]) continue;

            if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else {
                ans = Math.max(ans, count);
                count = 1;
            }
        }
        // 最后一个元素都是连续的，没有进入 else 更新 ans
        // 故最后返回 Math.max(ans, count)，从而实现更新 ans
        return Math.max(ans, count);
    }

    // KeyPoint 方法二 哈希查找解法
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        // 一开始将区间设置为不合法区间 [0,-1]
        int ans = 1, start = 0, end = -1;
        // 遍历一遍数组
        for (int num : nums) {

            // if (set.contains(num - 1)) continue;
            // 这里还是会存在重复计算，为什么会产生以及如何解决
            // 请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ

            // 如下遇到 0 1 2 3 4 0 0 0 0 0 0 0 0 0 0 0 5
            // 遇到从第二个 0 及其后面的 0 的时候，不会执行 continue，而是重复计算 ans

            // KeyPoint 优化：区间的方式来规避重复计算
            // debug 测试过，确实能减少重复计算，但是实际执行时间却增加了，主要还是和测试用例有关
            if (num >= start && num <= end) continue;

            int currNum = num;
            // 将当前 num 设置为起始区间
            start = currNum;
            int count = 1;
            // 可能存在多个连续数字，故使用循环判断，而不是使用 if 单次判断
            while (set.contains(currNum + 1)) {
                currNum += 1;
                count += 1;
            }
            end = currNum;
            ans = Math.max(ans, count);
        }

        return ans;
    }
}
