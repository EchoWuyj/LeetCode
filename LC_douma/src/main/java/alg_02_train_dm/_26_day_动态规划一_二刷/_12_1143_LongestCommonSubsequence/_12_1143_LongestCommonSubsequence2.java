package alg_02_train_dm._26_day_动态规划一_二刷._12_1143_LongestCommonSubsequence;

/**
 * @Author Wuyj
 * @DateTime 2023-06-18 20:49
 * @Version 1.0
 */
public class _12_1143_LongestCommonSubsequence2 {

    // KeyPoint 方法二 压缩状态(一)
    public int longestCommonSubsequence(String text1, String text2) {

//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                    dp[i][j] = 1 + dp[i - 1][j - 1];
//                } else {
//                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
//                }
//            }
//        }

        // 优化 => 压缩状态

        // 坐标相对位置
        // (i-1, j-1)  (i-1,j)
        // (i,j-1)     (i, j)
        // 当前的状态依赖于 '上一行' 和 '当前行' 的两行的状态值
        // 故只需要 2 行来存储状态即可，两行交替使用，通过 % 来实现

        // text1： a b c d e
        //           i
        // text2：a c e
        //              j

        // dp 数组
        //     0 1 2 3
        //  0  0 0 0 0
        //  1  0 1 1 1

        // text1： a b c d e
        //             i
        // text2：a c e
        //             j

        // dp 数组
        //     0 1 2 3
        //  0  0 1 1 1
        //  1  0 1 1 1

        int m = text1.length();
        int n = text2.length();

        // 调换字符串顺序可以减少空间的使用
        // => 保证 m >= n，这样二维 dp 数组(矩阵)竖向，这样使用的列数较少，且两行，从而节省空间
        // => 不要遗漏
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        int[][] dp = new int[2][n + 1];

        for (int i = 1; i <= m; i++) {
            int currRow = i % 2;
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 单个 i 位置，直接使用 currRow 替换
                    // i-1 => 1-currRow，而不能直接 currRow-1，否则越界
                    // 保证 dp[][] 行索引取值只能在 0 和 1 之间
                    dp[currRow][j] = 1 + dp[1 - currRow][j - 1];
                } else {
                    dp[currRow][j] =
                            Math.max(dp[currRow][j - 1], dp[1 - currRow][j]);
                }
            }
        }
        // 根据 m 的长度来返回 dp，即：当 i = m 时，currRow = m % 2;
        // 若 m = 1，则在第二行 (1,n) 位置
        // 若 m = 2，则在第二行 (0,n) 位置
        return dp[m % 2][n];
    }
}
