package alg_02_train_dm._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 16:14
 * @Version 1.0
 */
public class _05_221_maximal_square {
     /*

        221. 最大正方形
        在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
        输入：matrix = [['1','0','1','0','0'],
                       ['1','0','1','1','1'],
                       ['1','1','1','1','1'],
                       ['1','0','0','1','0']]
        输出：4

        提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 300
        matrix[i][j] 为 '0' 或 '1'

     */

    // KeyPoint 动态规划
    // 正方形面积：边长^2
    // => 简化问题
    // => 二维矩阵内最大正方形的边长
    public int maximalSquare1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLen = 0;

        // dp[i][j] 表示以 [i, j] 这个元素为'右下角'的最大的正方形的边长
        // 一般和坐标有关都是选择 [i, j] 作为状态参数
        int[][] dp = new int[m][n];

        // 通过填表，找规律，从而分析出状态转移方程
        // dp[i][j] 一般都是从四周，上下左右，左右斜上，左右斜下
        // => 从而分析出：dp[i][j] 和 dp[i-1][j-1]，dp[i-1][j]，...，之间的关系

        // 第一列
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                // 每次都要更新 maxLen，不要遗漏了
                maxLen = Math.max(maxLen, dp[i][0]);
            }
        }

        // 第一行
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxLen = Math.max(maxLen, dp[0][i]);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    // 木桶效应，选择周边最短 dp，
                    // 同时 dp[i][j] 需要还需要 +1，体现 matrix[i][j] == '1' 带来的变化，从而对 dp[i][j] 更新
                    dp[i][j] = Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    // 最大的正方形的边长
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        // 最大的正方形的边长^2 => 最大面积
        return maxLen * maxLen;
    }

    // KeyPoint 动态规划 + 合并状态初始化
    public int maximalSquare2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxLen = 0;

        // dp[i][j] 表示以 [i, j] 这个元素为'右下角'的最大的正方形的边长
        // 合并状态初始化
        // => 行的长度和列的长度都增加 1，有利于边界条件的处理
        // => 将第一行和第一列并入到状态转移方程中，不要单独额外处理，学习这种方式
        int[][] dp = new int[m + 1][n + 1];

        // KeyPoint 修改 dp[m+1][n+1] 后，对应的 i 和 j 需要进行变换
        // 修改 i 和 j 从 1 开始
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 修改 i,j 变成 i-1,j-1
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }

    // KeyPoint 动态规划 + 状态压缩(压缩为一维数组)
    // => 没有仔细看，感觉压缩状态有点麻烦，后面有空再看吧
    // 计算当前的状态 dp[i][j] 只依赖于上(preRow)、左上 (preRowPreCol) 以及左边 (dp[j - 1]) 三个状态
    // 注意不是：dp[i][j] 依赖的不仅是：上一行 dp[i-1][j] 或者 下一行 dp[i+1][j]，还有 dp[i-1][j-1]，故不能直接将行索引去掉
    // 对于 preRow 和 preRowPreCol 的计算逻辑请参考：第 26 天的力扣 1143 号算法题
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxLen = 0;

        // 状态压缩为一维数组
        // 行的长度和列的长度都增加 1，有利于边界条件的处理
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int preRow = 0;
            int preRowPreCol = 0;
            for (int j = 1; j <= n; j++) {
                preRowPreCol = preRow;
                preRow = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(dp[j - 1],
                            Math.min(preRowPreCol, preRow)) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    // 对于以 0 为右下角的最大正方形边长设置为 0
                    // 这里必须加上，因为经过若干个循环，dp[j] 已经不等于 0 了
                    dp[j] = 0;
                }
            }
        }
        return maxLen * maxLen;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'}
        };
        int ans = new _05_221_maximal_square().maximalSquare(matrix);
        System.out.println(ans);
    }
}
