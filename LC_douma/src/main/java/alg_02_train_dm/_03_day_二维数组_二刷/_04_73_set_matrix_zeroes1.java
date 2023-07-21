package alg_02_train_dm._03_day_二维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 11:16
 * @Version 1.0
 */
public class _04_73_set_matrix_zeroes1 {
    /*

        73. 矩阵置零
        给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
        请使用 原地 算法。

        进阶
        一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
        一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
        你能想出一个仅使用常量空间的解决方案吗？

        提示
        m == matrix.length
        n == matrix[0].length
        1 <= m, n <= 200
        -2^31 <= matrix[i][j] <= 2^31 - 1

     */

    // KeyPoint 方法一 使用额外两数组标记 0 所在行和列
    // 不能直接对原矩阵修改，因为矩阵修改后的数值 0 和原始数值 0，两者产生了混淆
    // 时间复杂度 O(mn(m+n))
    // 空间复杂度 O(mn)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] tmpMatrix = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                tmpMatrix[row][col] = matrix[row][col];
            }
        }

        // O(mn(m+n))
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 标记的行或列，需要将其设置为 0
                if (matrix[row][col] == 0) {
                    // 3 层 for 循环
                    for (int tmpRow = 0; tmpRow < m; tmpRow++) {
                        tmpMatrix[tmpRow][col] = 0;
                    }

                    for (int tmpCol = 0; tmpCol < n; tmpCol++) {
                        tmpMatrix[row][tmpCol] = 0;
                    }
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = tmpMatrix[row][col];
            }
        }
    }

    // KeyPoint 方法二 使用额外两数组标记 0 所在行和列
    // 时间复杂度 O(mn)
    // 空间复杂度 O(m+n)
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // KeyPoint 通过额外数组来标记二维数组中行和列位置信息
        // 1.遍历原始二维数组过程中，决定那一行，那一列设置为 0
        // 2.使用两个数组来标记，记录每行或者每列是否包含 0
        boolean[] rows = new boolean[m]; // m 行
        boolean[] cols = new boolean[n]; // n 列

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == 0) {
                    rows[row] = true;
                    cols[col] = true;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 标记的行或列，需要将其设置为 0
                if (rows[row] || cols[col]) {
                    matrix[row][col] = 0;
                }
            }
        }
    }
}
