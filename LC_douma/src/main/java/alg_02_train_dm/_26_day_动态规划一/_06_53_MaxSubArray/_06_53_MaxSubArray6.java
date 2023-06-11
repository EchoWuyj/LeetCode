package alg_02_train_dm._26_day_动态规划一._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 12:12
 * @Version 1.0
 */
public class _06_53_MaxSubArray6 {

    // 动态规划(改变状态定义)
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int maxSubArray(int[] nums) {

        int n = nums.length;

        // 动态规划：状态定义
        // dp[i][j] 表示子数组 [i, j] 的累加和
        // => 一般将状态值定义为求解的问题
        // => 修改成：本题所求问题
        // => dp[i][j] 表示子数组 [i, j] 的最大子数组和
        // 将二维降成一维 => 区间 [i,j] 中最大数组之和在 [0,j] 中
        // => dp[i] 表示以索引为 i 的元素结尾的最大子数组和
        int[] dp = new int[n];

        // 状态初始化
        dp[0] = nums[0];
        int maxSum = dp[0];
        // 状态转移
        for (int i = 1; i < n; i++) {
            // 根据值大小，选择是否加上 dp[i-1]，要么加上，要么不加
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int res = new _06_53_MaxSubArray6().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(res);
    }
}
