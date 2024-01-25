package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _85_128_longestConsecutive {

    // 最长 连续 序列
    // 给定一个未排序的整数数组 nums 找出数字连续的最长序列(不要求序列元素在原数组中连续)的长度。

    public int longestConsecutive(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n <= 1) return n;
        Set<Integer> set = new HashSet<>();
        int res = 1;
        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            int cur = num;
            int count = 1;
            // 说明遍历 cur-1 时，已经遍历过了 cur，故将 cur 跳过
            if (set.contains(cur - 1)) continue;
            // 如果 set 存在 cur + 1，则进行循环遍历
            while (set.contains(cur + 1)) {
                cur += 1;
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // KeyPoint 类似题目，好好区别
    // _85_128_longestConsecutive          最长连续序列      √
    // _85_674_findLengthOfLCIS            最长连续递增序列  √
    // _27_300_lengthOfLIS                 最长递增子序列    √
    // _36_1143_longestCommonSubsequence   最长公共子序列    √
}
