package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 20:03
 * @Version 1.0
 */
public class _07_53_maxSubArray {
    public int maxSubArray(int[] nums) {
        // 找出具有最大和的连续子数组
        int n = nums.length;
        // 最大和的连续子数组
        // dp[i] 表示以索引为 i 的元素结尾的最大子数组和
        int[] dp = new int[n];
        dp[0] = nums[0];
        // 从 dp[0] 开始
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            // 以 nums[i] 为中心，加上 dp[i - 1] 和不加上 dp[i - 1]
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            // 更新 max 值
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
