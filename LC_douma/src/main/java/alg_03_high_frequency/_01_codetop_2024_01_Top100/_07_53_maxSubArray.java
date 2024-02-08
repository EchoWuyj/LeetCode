package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 20:03
 * @Version 1.0
 */
public class _07_53_maxSubArray {

    // 最大子数组和
    // => 找出具有最大和的连续子数组(子数组最少包含一个元素)
    // 动态规划
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i] 表示以索引为 i 的元素结尾的最大子数组和
        int[] dp = new int[n];
        dp[0] = nums[0];
        // 从 dp[0] 开始
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            // 以 nums[i] 为中心进行判断
            // 1.加上 dp[i - 1]，nums[i] 和 dp[i - 1] 相加
            // 2.不加上 dp[i - 1]，只是 nums[i] 自己
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            // 更新 max 值
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
