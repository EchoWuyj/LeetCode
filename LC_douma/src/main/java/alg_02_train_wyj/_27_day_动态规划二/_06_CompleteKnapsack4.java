package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 11:27
 * @Version 1.0
 */
public class _06_CompleteKnapsack4 {

    public int knapsackComplete(int[] w, int[] v, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < w.length; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack4 k = new _06_CompleteKnapsack4();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10)); // 46
    }
}
