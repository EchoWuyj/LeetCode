package alg_02_train_dm._03_day_二维数组;

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
     */

    public int[][] generateMatrix1(int n) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int row = 0, col = 0;
        int di = 0;
        int[][] res = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        for (int i = 0; i < n * n; i++) {
            // 先是 row，再是 col，低级错误不能犯
            res[row][col] = i + 1;
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

    // KeyPoint 记录 bug：一般遇到矩阵大体结构正确但矩阵元素错位，基本都是 matrix 的 row，col 坐标出现问题，重点分析相关代码
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;

        int i = 1;
        while (startRow <= endRow && startCol <= endCol) {
            // top 行
            for (int col = startCol; col <= endCol; col++) res[startRow][col] = i++;
            // right 列
            for (int row = startRow + 1; row <= endRow; row++) res[row][endCol] = i++;
            if (startRow < endRow && startCol < endCol) {
                // bottom 行
                for (int col = endCol - 1; col > startCol; col--) res[endRow][col] = i++;
                // left 列
                for (int row = endRow; row > startRow; row--) res[row][startCol] = i++;
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
}
