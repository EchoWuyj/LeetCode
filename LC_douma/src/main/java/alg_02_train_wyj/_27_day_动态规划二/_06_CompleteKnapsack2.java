package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 11:02
 * @Version 1.0
 */
public class _06_CompleteKnapsack2 {

    public int knapsackComplete(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n][capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            dp[0][i] = (i / w[0]) * v[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                int count = j / w[i];
                for (int k = 0; k < count; k++) {
                    dp[i][j] = Math.max(dp[i][j], k * v[i] + dp[i - 1][j - k * w[i]]);
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack2 k = new _06_CompleteKnapsack2();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10)); // 46
    }


}
