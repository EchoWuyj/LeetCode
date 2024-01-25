package alg_03_high_frequency._01_codetop_2024_01;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 15:33
 * @Version 1.0
 */
public class _27_300_lengthOfLIS_02 {
    public int lengthOfLIS(int[] nums) {

        // 最长递增子序列
        // 题目：给你一个整数数组 nums，找到其中最长 严格递增 子序列的长度。

        // 判空
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;

        // dp[i] 表示以 nums[i] 结尾时，最长递增子序列的长度
        int[] dp = new int[n];
        // 初始长度为 1
        int max = 1;
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            // 子序列可以不连续，故每次个位置 i 对应的 j 需要从 0 开始判断
            for (int j = 0; j < i; j++) {
                // 只有 nums[i] > nums[j] 保证递增，才会去更新
                if (nums[i] > nums[j]) {
                    // dp[i] 和 dp[j] + 1 比较
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    // 更新
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
