package alg_02_train_dm._03_day_二维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 16:41
 * @Version 1.0
 */
public class _06_59_spiral_matrix_ii {


    /*
        59. 螺旋矩阵 II
        给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，
        且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

         输入：n = 3
         输出：[[1,2,3],[8,9,4],[7,6,5]]

         1 → 2 → 3
                 ↓
         8 → 9   4
         ↑       ↓
         7 ← 6 ← 5

         提示：
         1 <= n <= 20

     */

    public int[][] generateMatrix1(int n) {
        // KeyPoint
        // 右 下 左 上
        // 对应 i 和 j 坐标位置交替的
        // => {0, 1}, {1, 0}
        // => {0, -1}, {-1, 0}
        // 右 左 => [0][±1]
        // 上 下 => [±1][0]
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int row = 0, col = 0;
        int di = 0;
        // 申请 res 结果矩阵
        int[][] res = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        for (int i = 0; i < n * n; i++) {
            // 给 res 矩阵 赋值
            // 先是 row，再是 col，低级错误不能犯
            res[row][col] = i + 1;
            // KeyPoint 提交 OJ 将输出注释掉，否则影响性能
            // System.out.println(i + 1);
            seen[row][col] = true;

            int nextRow = row + dirs[di][0];
            int nextCol = col + dirs[di][1];

            if (nextRow < 0 || nextRow >= n
                    || nextCol < 0 || nextCol >= n
                    || seen[nextRow][nextCol]) {
                // 改变方向
                di = (di + 1) % 4;
            }
            row = row + dirs[di][0];
            col = col + dirs[di][1];
        }
        return res;
    }

    // KeyPoint 解决 bug 经验
    // 一般遇到矩阵整体结构正确，但矩阵元素错位 => 基本都是 i，j 坐标出现问题
    // 总结：i 和 j 存在的问题
    // 1.for 循环条件，边界取等与不取等
    // 2.for 循环变量，i 和 j 写混淆
    // 3.matrix i 和 j 坐标相反 => 将 matrix[i][j]，写错了 matrix[j][i]
    public int[][] generateMatrix(int n) {

        // 定义结果矩阵 res
        int[][] res = new int[n][n];

        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;
        int num = 1;
        while (startRow <= endRow && startCol <= endCol) {
            // top 行
            for (int col = startCol; col <= endCol; col++) res[startRow][col] = num++;
            // right 列
            for (int row = startRow + 1; row <= endRow; row++) res[row][endCol] = num++;
            if (startRow < endRow && startCol < endCol) {
                // bottom 行
                for (int col = endCol - 1; col > startCol; col--) res[endRow][col] = num++;
                // left 列
                for (int row = endRow; row > startRow; row--) res[row][startCol] = num++;
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
}
