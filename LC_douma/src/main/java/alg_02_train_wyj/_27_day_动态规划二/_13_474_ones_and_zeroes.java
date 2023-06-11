package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 20:33
 * @Version 1.0
 */
public class _13_474_ones_and_zeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] counts = help(str);
            int zeros = counts[0];
            int ones = counts[1];
            for (int zero = m; zero >= zeros; zero--) {
                for (int one = n; one >= ones; one--) {
                    dp[zero][one] = Math.max(dp[zero][one], 1 + dp[zero - zeros][one - ones]);
                }
            }
        }
        return dp[m][n];
    }

    public int[] help(String str) {
        int[] arr = new int[2];
        for (char c : str.toCharArray()) {
            arr[c - '0']++;
        }
        return arr;
    }
}
