package alg_02_train_dm._26_day_动态规划一_2刷._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 12:11
 * @Version 1.0
 */
public class _06_53_MaxSubArray3 {

    // 动态规划
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n^2) => 超出内存限制
    public int maxSubArray(int[] nums) {

        int n = nums.length;

        // 状态定义：dp[i][j] 表示子数组 [i, j] 的累加和
        int[][] dp = new int[n][n];

        // 状态初始化
        dp[0][0] = nums[0];

        // int maxSum = Integer.MIN_VALUE;
        // maxSum 不能初始化为 Integer.MIN_VALUE

        // 若 nums 只有一个元素，则应该是 nums[0]，不执行下面 for 循环
        // 则 maxSum 没有被覆盖，还是 Integer.MIN_VALUE

        int maxSum = dp[0][0];

        // 因为在 dp 矩阵，第一行属于临界位置，和一般位置不同，需要单独计算赋值
        // 右斜下对角线在矩阵中，属于一般位置，可以通过通用的状态转移方程进行赋值

        // j 从 1 开始，即从第二个元素开始计算，dp
        // KeyPoint 定义规则：
        // i 一般表示行，从上往下遍历，使用 i 来遍历
        // j 一般表示列，从左往右遍历，使用 j 来遍历

        // 使用 j 表示遍历列
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + nums[j];
            maxSum = Math.max(maxSum, dp[0][j]);
        }
        // 状态转移：另外一种形式
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 直接将 dp[0] 固定
                // 注意：这里是 i-1，不是 i
                dp[i][j] = dp[0][j] - dp[0][i - 1];
                // num -2 1 -3 4 -1 2
                //           ↑    ↑
                //           i    j
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }
        return maxSum;
    }

    // 状态转移方程
    // dp[i][j] = dp[0][j] - dp[0][i - 1];
    // dp[0][j] = dp[0][j - 1] + nums[j];
    // => 只和第一行有关，可以将横坐标 i 去除，压缩状态
    // => 超出时间限制
    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[j] = dp[j] - dp[i - 1];
                maxSum = Math.max(maxSum, dp[j]);
            }
        }
        return maxSum;
    }
}
