package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 13:53
 * @Version 1.0
 */
public class _01_867_transpose_matrix {
    /*

        867. 转置矩阵
        给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
        矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。

        1 2 3    1 4 7
        4 5 6 => 2 5 8
        7 8 9    3 6 9

        提示
        m == matrix.length
        n == matrix[i].length => m 和 n 不一定相等
        1 <= m, n <= 1000
        1 <= m * n <= 10^5
        -10^9 <= matrix[i][j] <= 10^9

        示例 1：
        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[[1,4,7],[2,5,8],[3,6,9]]

        示例 2：
        输入：matrix = [[1,2,3],[4,5,6]]
        输出：[[1,4],[2,5],[3,6]]
     */

    // 矩阵转置 => 交换每个元素行列坐标 => [i,j] -> [j,i]
    public int[][] transpose(int[][] matrix) {
        // m 和 n 不一定相等
        int m = matrix.length;
        int n = matrix[0].length;
        // mn 矩阵 -> nm 矩阵 => 故需要重新申请一个新的数组，不能基于原数组修改
        int[][] t = new int[n][m];
        for (int i = 0; i < m; i++) { // 行
            for (int j = 0; j < n; j++) { // 列
                // 交换行列索引
                t[j][i] = matrix[i][j];
            }
        }

        return t;
    }
}
