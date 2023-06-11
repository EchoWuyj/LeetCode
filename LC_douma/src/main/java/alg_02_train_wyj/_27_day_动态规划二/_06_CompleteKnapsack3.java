package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 11:18
 * @Version 1.0
 */
public class _06_CompleteKnapsack3 {

    public int knapsackComplete(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            dp[i] = (i / w[0]) * v[0];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                int count = (j / w[i]);
                for (int k = 0; k < count; k++) {
                    dp[j] = Math.max(dp[j], k * v[i] + dp[j - k * w[i]]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack3 k = new _06_CompleteKnapsack3();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10)); // 46
    }
}
