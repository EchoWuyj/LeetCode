package alg_02_train_dm._26_day_动态规划一._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 11:51
 * @Version 1.0
 */
public class _06_53_MaxSubArray3 {

    // 动态规划
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    public int maxSubArray(int[] nums) {

        // 状态定义：dp[i] 表示子数组 [0, i] 的累加和
        int[] dp = new int[nums.length];

        // 状态初始化
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }
        // 状态转移
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // 优化：当前 i 行状态，取决于 i-1 行状态 => 二维压缩一维，直接去掉一维即可
                dp[j] = dp[j] - dp[i - 1];
                maxSum = Math.max(maxSum, dp[j]);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int res = new _06_53_MaxSubArray3().maxSubArray(new int[]{2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(res);
    }
}
