package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 19:36
 * @Version 1.0
 */
public class _06_Knapsack4 {

    public int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            dp[i] = (i >= w[0] ? v[0] : 0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_Knapsack4 k = new _06_Knapsack4();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsack(w, v, 10)); // 35
    }
}
