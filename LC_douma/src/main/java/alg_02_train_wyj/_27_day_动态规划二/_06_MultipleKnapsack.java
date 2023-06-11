package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 11:51
 * @Version 1.0
 */
public class _06_MultipleKnapsack {

    public int knapsack(int[] w, int[] v, int[] p, int capacity) {
        int n = w.length;
        if (n == 0) return 0;

        int newN = 0;
        for (int i = 0; i < n; i++) {
            newN += p[i];
        }

        int[] newW = new int[newN];
        int[] newV = new int[newN];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p[i]; j++) {
                newW[index] = w[i];
                newV[index] = v[i];
                index++;
            }
        }

        int[] dp = new int[capacity + 1];
        for (int i = 0; i < newN; i++) {
            for (int j = capacity; j >= newW[i]; j--) {
                dp[j] = Math.max(dp[j], newV[i] + dp[j - newW[i]]);
            }
        }
        return dp[capacity];
    }
}
