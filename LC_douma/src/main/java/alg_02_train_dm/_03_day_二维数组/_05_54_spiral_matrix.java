package alg_02_train_dm._03_day_二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 13:28
 * @Version 1.0
 */
public class _05_54_spiral_matrix {

    /*
        54. 螺旋矩阵
        给你一个 m 行 n 列的矩阵 matrix
        请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

        提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100

        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[1,2,3,6,9,8,7,4,5]

        输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        输出：[1,2,3,4,8,12,11,10,9,5,6,7]

     */

    // KeyPoint 方法一 纯直接模拟法，右下左上遍历
    public List<Integer> spiralOrder1(int[][] matrix) {

        // x -> 上下 -> x 正负半轴
        // y -> 左右 -> y 正负半轴
        // KeyPoint 方向 2 点判断
        // 1 先定 x，y -> 水平，垂直
        // 2 再定 +，- -> 上下，左右
        // 定义 dirs 保证 => 右下左上，方便后续通过 di 来控制方向，dirs 为 4*2 矩阵
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 4*2

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0, col = 0;
        // 控制 dirs 方向
        int di = 0;
        List<Integer> res = new ArrayList<>();
        boolean[][] seen = new boolean[m][n];
        // m*n 个元素，遍历 m*n 次
        for (int i = 0; i < m * n; i++) {
            // KeyPoint for 循环中初始化变量，需要特别注意，是否需要在一轮循环之后，重新清空再去创建

            // List<Integer> temp = new ArrayList<>();
            // 每轮 for 循环都重新创建一个 temp

            // int row = 0, col = 0;
            // 而这里不能在 for 循环中定义，否则每次 for 循环都会重新初始化 row 和 col

            res.add(matrix[row][col]);
            seen[row][col] = true;

            // 下个位置索引
            int nextRow = row + dirs[di][0];
            int nextCol = col + dirs[di][1];

            // 下个位置索引不合法，即'索引越界'或者'遇到已经访问过的元素' => 改变遍历方向，右下左上
            // seen[nextRow][nextCol] 本身就是 boolean，不需要 seen[nextRow][nextCol] == true
            // m 和 n 边界是可以取等
            if (nextRow < 0 || nextRow >= m
                    || nextCol < 0 || nextCol >= n
                    // KeyPoint 统一对 (nextRow,nextCol) 进行判断，中间不能混进 (row,col)
                    || seen[nextRow][nextCol]) {
                di = (di + 1) % 4;
            }

            // 下个位置索引合法，去往下个位置，修改当前位置索引
            row = row + dirs[di][0];
            col = col + dirs[di][1];
        }
        return res;
    }

    // KeyPoint 方法二 按层模拟 => 每次遍历一整行，或者一整列
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // 指针定义：开始行，结束行；开始列，结束列
        // endRow，endCol 作为数组索引坐标需要 -1
        // 而 m，n 单纯作为 while 循环判断次数是不需要 -1
        int startRow = 0, endRow = matrix.length - 1;
        int startCol = 0, endCol = matrix[0].length - 1;

        // 只要其中一堆指针不满足，就结束 while 循环
        while (startRow <= endRow && startCol <= endCol) {
            // top 行
            // matrix 固定不变的索引 startRow 和其位置有关
            for (int col = startCol; col <= endCol; col++) res.add(matrix[startRow][col]);
            // right 列
            for (int row = startRow + 1; row <= endRow; row++) res.add(matrix[row][endCol]);
            // 边界情况，即一行一列，不执行以下代码
            if (startRow < endRow && startCol < endCol) {
                // KeyPoint 注意从右往左遍历，for 循环中变量是自减，不是自增
                // bottom 行
                for (int col = endCol - 1; col > startCol; col--) res.add(matrix[endRow][col]);
                // left 列
                for (int row = endRow; row > startRow; row--) res.add(matrix[row][startCol]);
            }
            // 一轮 for 循环结束，更新索引
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
}
