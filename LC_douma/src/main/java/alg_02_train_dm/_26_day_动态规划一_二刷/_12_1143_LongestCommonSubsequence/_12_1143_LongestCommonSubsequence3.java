package alg_02_train_dm._26_day_动态规划一_二刷._12_1143_LongestCommonSubsequence;

/**
 * @Author Wuyj
 * @DateTime 2023-06-18 20:50
 * @Version 1.0
 */
public class _12_1143_LongestCommonSubsequence3 {

    // KeyPoint 方法三 压缩状态(二)
    //  => 确实能提高性能，在所有 Java 提交中击败了 76.33% 的用户
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 调换字符串顺序可以减少空间的使用
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }

        // 坐标相对位置
        // (i-1, j-1)  (i-1,j)
        // (i,j-1)     (i, j)

        // 当前的状态依赖于 '上一行' 和 '当前行' 的状态值
        // 若想要压缩状态，即使用一维数组来代替二维数组，则 '上一行' 不要了
        // =>   if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
        //                    dp[i][j] = 1 + dp[i - 1][j - 1];
        //
        //                    // 注意 1：
        //                    // 区间 [i,j] 范围 dp，是 dp[i][j] 与 dp[i+1][j-1]
        //                    // 但这里是两个字符串，逐一字符比较，是 dp[i][j] 与 dp[i-1][j-1]
        //
        //                    // 注意 2：
        //                    // 最长公共子序列，text1 和 text2 字符相同算 1 字符，故 +1
        //                    // 最长回文子序列，首尾相同，算作 2 个字符，故 +2
        //                } else {
        //                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);将 dp[i-1][j-1] 和 dp[i-1][j] 记录在 preRowPreCol 和 preRow 两个变量中，从而减少一行

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int preRow = 0;
            int preRowPreCol = 0;
            for (int j = 1; j <= n; j++) {
                // 每次内层 for 循环一开始，都是从外层 for 循环 定义的 0 开始
                // 之前的 preRow 变成现在的 preRowPreCol
                preRowPreCol = preRow;
                // for 循环一轮之后，j 又从 1 开始循环遍历，又回到第 2 列位置
                // 此时 dp 数组已经变成上一行状态数组，dp[j] 为对应位置 j 的上一行状态值
                preRow = dp[j];
                // 将 dp[i-1][j-1] 和 dp[i-1][j] 替换成 preRowPreCol 和 preRow
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + preRowPreCol;
                } else {
                    dp[j] = Math.max(preRow, dp[j - 1]);
                }
            }
        }

        return dp[n];
    }
}
