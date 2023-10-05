package alg_02_train_dm._03_day_二维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 13:28
 * @Version 1.0
 */
public class _05_54_spiral_matrix1_推荐 {

    /*
        54. 螺旋矩阵
        给你一个 m 行 n 列的矩阵 matrix
        请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[1,2,3,6,9,8,7,4,5]

        1 → 2 → 3
                ↓
        4 → 5   6
        ↑       ↓
        7 ← 8 ← 9

        输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        输出：[1,2,3,4,8,12,11,10,9,5,6,7]

        提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100


     */

    // KeyPoint 方法一 纯直接模拟法，右下左上遍历
    public List<Integer> spiralOrder1(int[][] matrix) {

        // KeyPoint 坐标系下的 x 和 y 含义
        // x => 上下 => x 正负半轴 => 水平方向(平行 x 轴)
        // y => 左右 => y 正负半轴 => 垂直方向(平行 y 轴)

        // KeyPoint 矩阵下 x 和 y 含义
        // [0,0] [0,1] [0,2]
        // [1,0] [1,1] [1,2]
        // [2,0] [2,1] [2,2]

        // 其中：
        // [0,0] => [0,1] 对应：[0,1]
        // [0,2] => [1,2] 对应：[1,0]
        // [2,2] => [2,1] 对应：[0,-1]
        // [2,0] => [1,0] 对应：[-1,0]

        // KeyPoint 注意：不能使用坐标系下的 x 和 y 含义，推导矩阵下 x 和 y 含义

        // 确定方向，有 2 点判断
        // 1.先定 x，y
        // 2.再定 +，-

        // KeyPoint 注意 dirs 方向顺序
        // 一般情况：在回溯中对 dirs 方向是没有要求的
        // 本题要求：顺时针螺旋顺序，故 dir 方向是有要求的
        // => 保证：右下左上，和顺时针螺旋顺序保持一致
        // => 方便后续通过 dir 来控制方向，dirs 整体是 4*2 矩阵

        // 定义 dirs
        // 往右，往下，都是 +1
        // 往上，往左，都是 -1
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int m = matrix.length;
        int n = matrix[0].length;

        // 从 (0,0) 位置开始遍历
        int row = 0, col = 0;
        // 控制 dirs 方向
        int dir = 0;
        // 存储遍历中遇到矩阵元素
        List<Integer> res = new ArrayList<>();
        boolean[][] seen = new boolean[m][n];
        // 一共有 m*n 个元素，故需要遍历 m*n 次
        for (int i = 0; i < m * n; i++) {

            // KeyPoint for 循环中开始位置，初始化变量
            // 若在 for 循环中开始位置，初始化变量，则在一轮循环之后，都会重新清空再去创建，变量值不会保存
            // List<Integer> list = new ArrayList<>();
            // 每轮 for 循环都重新创建一个 list
            // int row = 0, col = 0;
            // 本题不能在 for 循环中定义，否则每次 for 循环都会重新初始化 row 和 col
            // 总结：根据题目的需要，从而决定是在 for 循环里面创建变量，还是在 for 循环外面创建变量

            res.add(matrix[row][col]);

            //  1 → 2 → 3
            //  ↑       ↓
            //  4 → 5   6
            //  ↑       ↓
            //  7 ← 8 ← 9

            // 记录已经访问过的元素
            // 若遍历到已经访问过的元素，修改 dir 方向
            // 如：4 → 1，其中 1 是已经访问过的，需要修改方向 4 → 5
            seen[row][col] = true;

            // 计算下个位置索引
            int nextRow = row + dirs[dir][0];
            int nextCol = col + dirs[dir][1];

            // 确定 dir 方向
            // 1.若下个位置索引不合法，即'索引越界'或者'遇到已经访问过的元素' => 改变遍历方向，右下左上
            // 2.seen[nextRow][nextCol] 本身就是 boolean，不需要 seen[nextRow][nextCol] == true
            // 3.注意：越界情况，m 和 n 边界是可以取等
            if (nextRow < 0 || nextRow >= m
                    || nextCol < 0 || nextCol >= n
                    // KeyPoint 注意：统一对 (nextRow,nextCol) 进行判断，中间不能混进 (row,col)
                    || seen[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
            }

            // 下个位置索引合法，去往下个位置，修改当前位置索引
            // KeyPoint 给 [row,col] 重新赋值
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];
        }
        return res;
    }
}
