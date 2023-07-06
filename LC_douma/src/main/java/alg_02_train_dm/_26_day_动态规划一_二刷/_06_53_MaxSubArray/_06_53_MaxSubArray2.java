package alg_02_train_dm._26_day_动态规划一_二刷._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 11:24
 * @Version 1.0
 */
public class _06_53_MaxSubArray2 {

    // 本题满足
    // 1.可以通过穷举方式，求最优值问题
    // 2.存在重复计算
    // => 可以使用'动态规划'来解决

    // 动态规划
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n^2) => 超出内存限制
    public int maxSubArray(int[] nums) {

        int n = nums.length;

        // 状态定义
        // dp[i][j] 表示子数组 [i, j] 的累加和
        // 因为 j >= i，故只会在二维数组中右上半部分
        int[][] dp = new int[n][n];

        // 状态初始化
        dp[0][0] = nums[0];
        int maxSum = dp[0][0];

        for (int i = 1; i < n; i++) {
            // 初始化第一行，单独进行初始化
            dp[0][i] = dp[0][i - 1] + nums[i];
            // 计算每个状态 dp[i][j] 都是需要维护最大值 maxSum
            maxSum = Math.max(maxSum, dp[0][i]);
        }

        // 状态转移
        // 通过 for 循环遍历，将所有 [i,j] 情况都包括，避免遗漏
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {

                // 通过填表的方式，推导出状态转移方程
                // => 通过先举例，探索坐标关系，再去抽象成：纯字母形式
                // dp[1][2] = dp[0][2]- dp[0][0]
                // => dp[i][j] = dp[i-1][j]- dp[i-1][i-1]

                // 索引 0 1  2 3  4  5
                // num -2 1 -3 4 -1 2
                //        ↑  ↑
                //        i  j
                //      i    j
                //      ij

                // 注意：对角线 dp 状态也是可以通过下面公式计算出来，不需要单独赋值
                dp[i][j] = dp[i - 1][j] - dp[i - 1][i - 1];
                // 优化：当前 i 行状态，取决于 i-1 行状态
                // => 二维压缩一维，直接去掉一维即可
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }
        return maxSum;
    }



    // 动态规划 => 优化，压缩状态
    // 时间复杂度：O(n^2) => 超出时间限制
    // 空间复杂度：O(n)
    public int maxSubArray1(int[] nums) {

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


}
