package alg_02_train_dm._26_day_动态规划一._10_516_LongestPalindromeSubseq;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 15:29
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq3 {

    // 动态规划
    // 时间复杂度 O(n^2)
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();

        // 定义状态关键：
        // 1.状态参数 => [start,i] 区间范围
        // 2.状态值 => 状态值和所求问题保持一致 => 最长回文子序列的长度

        // 状态：dp[i][j] 表示在区间 [i,j] 中最长回文子序列的长度
        // 一般区间 [i,j] 对应 dp 数组都是二维的
        // 存储数值使用 int，存储 true / false 使用 boolean
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 字符串 "bbbab"
        // 字符数组 b b b a b
        // 索引     0 1 2 3 4

        // for 循环遍历顺序，决定填表顺序
        // 关键：填表的顺序，如何使用代码实现
        // 两种填表顺序：
        // 1.斜右 ↘ (不推荐，性能没有第2种好)
        // 2.从下往上 ↑，从前往后 → (推荐)

        // 本题：从下往上 ↑，从前往后 →
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 1.若 s.charAt(i) == s.charAt(j)，则 dp[i][j] = 2 + dp[i + 1][j - 1];
                // 通过 dp 索引关系，dp[i][j] 与 dp[i+1][j-1] 找到依赖关系
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    // 2.若 s.charAt(i) != s.charAt(j)，两端不相等，则两端不同时包括，而是分别只包括一端
                    // 故 dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
                    // 注意：这里不能直接跳过，i 和 j，dp[i][j] = dp[i + 1][j - 1]，这样是有遗漏解的
                    // dp[i + 1][j] 或 dp[i][j - 1] 认为是已知的，而 dp[i][j] 是推导出来的
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new _10_516_LongestPalindromeSubseq3()
                .longestPalindromeSubseq("bbbab"));
    }
}
