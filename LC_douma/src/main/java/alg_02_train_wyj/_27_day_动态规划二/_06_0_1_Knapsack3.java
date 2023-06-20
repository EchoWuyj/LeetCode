package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 17:55
 * @Version 1.0
 */
public class _06_0_1_Knapsack3 {
    public int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n][capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            dp[0][j] = (j >= w[0] ? v[0] : 0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j < w[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack3 k = new _06_0_1_Knapsack3();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};
        System.out.println(k.knapsack(w, v, 10)); // 35
    }
}
