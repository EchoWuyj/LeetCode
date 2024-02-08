package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:44
 * @Version 1.0
 */
public class _76_221_maximalSquare {

    // 最大正方形
    // 动态规划
    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        // 边长从 0 开始
        int maxLen = 0;
        // dp[i][j] 表示以 [i,j] 这个元素为'右下角'的最大的正方形的边长
        int[][] dp = new int[m][n];

        // 遍历行
        for (int i = 0; i < m; i++) {
            // KeyPoint 需要判断当前位置的字符值进行判断，只有'1'才是有效的
            if (matrix[i][0] == '1') {
                // j = 0 固定列，表示第一列
                // i 变化，表示不同行
                dp[i][0] = 1;
                maxLen = Math.max(maxLen, dp[i][0]);
            }
        }

        // 遍历列
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == '1') {
                // i = 0 固定行，表示第一行
                // j 变化，表示不同列
                dp[0][j] = 1;
                maxLen = Math.max(maxLen, dp[0][j]);
            }
        }

        // 从 [1,1] 位置开始，接着遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // KeyPoint 特别注意：只有遇到 '1' 才判断 dp 状态
                if (matrix[i][j] == '1') {
                    // 计算 dp[i][j] 需要加上当前位置 matrix[i][j] == '1'
                    // 即 dp[i][j] 结尾需要 +1，千万不要遗漏了。
                    dp[i][j] = Math.min(dp[i - 1][j],
                            Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    // 更新 maxLen
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        // 最后返回面积，而不是单纯的 maxLen
        return maxLen * maxLen;
    }
}
