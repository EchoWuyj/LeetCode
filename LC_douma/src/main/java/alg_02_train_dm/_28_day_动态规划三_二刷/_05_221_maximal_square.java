package alg_02_train_dm._28_day_动态规划三_二刷;

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
    // 正方形面积：边长^2 => 简化问题 => 二维矩阵内最大正方形的边长
    public int maximalSquare1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLen = 0;

        // dp[i][j] 表示以 [i,j] 这个元素为'右下角'的最大的正方形的边长
        // KeyPoint 一般和坐标有关都是选择 [i,j] 作为状态参数
        int[][] dp = new int[m][n];

        // 通过填表，找规律，从而分析出状态转移方程
        // dp[i][j] 一般都是从四周，上下左右，左右斜上，左右斜下
        // => 从而分析出：dp[i][j] 和 dp[i-1][j-1]，dp[i-1][j]，...，之间的关系

        // 第一列
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                // KeyPoint 每次都要更新 maxLen，不要遗漏了
                maxLen = Math.max(maxLen, dp[i][0]);
            }
        }

        // 第一行
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                maxLen = Math.max(maxLen, dp[0][j]);
            }
        }

        // KeyPoint for 循环从 1 开始，对应 dp 关系是 i 和 i-1 以及 j 和 j-1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // KeyPoint 限制条件，只有 matrix[i][j] == '1' 时，才去更新 dp 状态
                if (matrix[i][j] == '1') {
                    // 木桶效应，选择周边最短 dp 值
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

    // KeyPoint 合并状态初始化 => 矩阵行列加一
    public int maximalSquare2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxLen = 0;

        // 合并状态初始化
        // => 行的长度和列的长度都增加 1，有利于边界条件的处理
        // => 将第一行和第一列并入到状态转移方程中，不要单独额外处理，学习这种方式
        int[][] dp = new int[m + 1][n + 1];

        // 修改 dp[m+1][n+1] 后，对应的 i 和 j 需要进行变换
        // 分析以下 3 个方面是否需要变换
        // 1.for 循环中，i 和 j 起始位置
        // 2.matrix[i][j] 坐标索引
        // 3.dp[i][j] 坐标索引

        // 修改 i 和 j 从 1 开始索引，同时 i 取等 m，j 取等 n
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

    // KeyPoint 合并状态初始化 => if 控制索引坐标
    public int maximalSquare3(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLen = 0;
        int[][] dp = new int[m][n];

        // 合并状态初始化 => 通过 if 来控制索引坐标
        // 关键保证 dp[i-1][j]，dp[i][j-1]，dp[i-1][j-1] 不越界
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if ((i == 0 && j == 0)
                            || (i == 0 && j != 0)
                            || (i != 0 && j == 0)) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j],
                                Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }

    // KeyPoint 状态压缩 => 压缩为一维数组
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxLen = 0;

        // 类似：第 26 天的力扣 1143 号算法题

        // 坐标相对位置
        // (i-1, j-1)  (i-1,j)
        // (i,j-1)     (i, j)

        // 当前的状态依赖于 '上一行' 和 '当前行' 的状态值
        // 若想要压缩状态，即使用一维数组来代替二维数组，则 '上一行' 不要了
        // => dp[i-1][j-1] => preRowPreCol
        //    dp[i-1][j]   => preRow
        // => 通过两个变量中，从而减少一行
        // => 注意：本题不符合：直接将行索引去掉的条件

        // 状态压缩为一维数组
        int[] dp = new int[n + 1];

        // 压缩为一维数组 => 3 步走(模板)
        for (int i = 1; i <= m; i++) {
            // 1.定义两个变量
            int preRow = 0;
            int preRowPreCol = 0;
            for (int j = 1; j <= n; j++) {
                // 2.状态转移前，基于上轮结果，对于本轮变量赋值
                preRowPreCol = preRow;
                preRow = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    // 3.替换状态转移中的 dp[i-1][j-1]，dp[i-1][j]
                    dp[j] = Math.min(dp[j - 1],
                            Math.min(preRowPreCol, preRow)) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {

                    // 对于 matrix[i-1][j-1] = '0'，dp[j] 得显示设置为 0
                    // => 因为压缩状态，数组空间被多次复用，原来的 dp[j] 默认值 0，
                    //    可能已经被其他值所覆盖了，故得显示加上：dp[j] = 0
                    // => 一般压缩状态 dp 代码汇总，因为默认赋值而省略的代码需要补上
                    dp[j] = 0;
                }
            }
        }
        return maxLen * maxLen;
    }
}
