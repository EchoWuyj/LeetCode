package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 20:38
 * @Version 1.0
 */
public class _02_48_rotate_image3 {

    // KeyPoint 方法三 原地'翻转'
    // 根据索引坐标关系，即 [row][col] 和 [col][n-row-1]，推理出矩阵变换操作
    // matrix[row][col] -> 水平翻转 -> matrix[n-row-1][col] -> 主对角线翻转 -> matrix[col][n-row-1]
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // KeyPoint 水平翻转，遍历 n/2 行
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < n; col++) {
                // [row][col] 被记录之后，就可以被覆盖了
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = tmp;
            }
        }
        // 因为是 n*n 矩阵，具有对称性，直接交换即可
        // 主对角线翻转
        for (int row = 0; row < n; row++) {
            // KeyPoint bug 修复：到 row 为止即可，要不然就翻转回来了
            for (int col = 0; col < row; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }
    }
}
