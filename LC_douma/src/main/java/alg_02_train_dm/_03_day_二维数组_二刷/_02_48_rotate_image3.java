package alg_02_train_dm._03_day_二维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 20:38
 * @Version 1.0
 */
public class _02_48_rotate_image3 {

    // KeyPoint 方法三 原地'翻转'
    public void rotate(int[][] matrix) {

        // 根据索引坐标关系：[row][col] => [col][n-row-1]
        // 推理出矩阵翻转操作：先水平翻转，再主对角线翻转

        // 1.水平翻转：第一行和最后一行对调，以此类推 ...
        // matrix[row][col] => matrix[n-row-1][col]

        // 2.主对角线翻转 [i][j] => [j][i]
        // matrix[n-row-1][col] => matrix[col][n-row-1]

        int n = matrix.length;
        // 1.水平翻转，遍历 n/2 行
        for (int row = 0; row < n / 2; row++) {
            // 每行的所有列
            for (int col = 0; col < n; col++) {
                // [row][col] 被记录之后，就可以被覆盖了
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = tmp;
            }
        }

        // 因为是 n*n 矩阵，具有对称性，直接交换即可
        // 2.主对角线翻转
        for (int row = 0; row < n; row++) {
            // KeyPoint 易错点
            // 矩阵主对角线，col < row 为止即可，否则就翻转回来了
            for (int col = 0; col < row; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }
    }
}
