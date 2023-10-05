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
            int[] count = count(strs[i]);
            int zero = count[0];
            int one = count[1];

            for (int j = m; j >= zero; j--) {
                for (int k = n; k >= one; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - one] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int[] count(String str) {
        int[] count = new int[2];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            count[str.charAt(i) - '0']++;
        }
        return count;
    }
}
