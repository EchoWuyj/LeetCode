package alg_02_train_dm._26_day_动态规划一._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 12:11
 * @Version 1.0
 */
public class _06_53_MaxSubArray4 {

    // 动态规划
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n^2)
    public int maxSubArray(int[] nums) {

        // 状态定义：dp[i][j] 表示子数组 [i, j] 的累加和
        int[][] dp = new int[nums.length][nums.length];

        // 状态初始化
        dp[0][0] = nums[0];
        int maxSum = dp[0][0];

        // i 从 1 开始，即从第二个元素开始计算，dp
        for (int i = 1; i < nums.length; i++) {
            dp[0][i] = dp[0][i - 1] + nums[i];
            maxSum = Math.max(maxSum, dp[0][i]);
        }
        // 状态转移：另外一种形式
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // 直接将 dp[0] 固定
                dp[i][j] = dp[0][j] - dp[0][i - 1];
                // num -2 1 -3 4 -1 2
                //           ↑    ↑
                //           i    j
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int res = new _06_53_MaxSubArray4().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(res);
    }
}
