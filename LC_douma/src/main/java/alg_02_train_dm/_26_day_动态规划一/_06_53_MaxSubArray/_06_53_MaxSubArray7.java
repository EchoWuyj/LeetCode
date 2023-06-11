package alg_02_train_dm._26_day_动态规划一._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 12:12
 * @Version 1.0
 */
public class _06_53_MaxSubArray7 {

    // 动态规划 (三) + 状态压缩
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int maxSubArray(int[] nums) {

        // 状态初始化
        // 计算 dp[i] 只是依赖 dp[i-1]，只是依赖一个状态
        // => 定义一个变量，记录前面一个状态即可，不需要记录前面所有的状态，从而实现'压缩状态dp'
        int dp = nums[0];
        int maxSum = dp;
        // 状态转移
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int res = new _06_53_MaxSubArray7().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(res);
    }
}
