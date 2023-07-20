package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 20:40
 * @Version 1.0
 */
public class _04_73_set_matrix_zeroes2 {

    // KeyPoint 方法二 使用原始二维数组的第一行，第一列来标记 0 所在行和列
    // 时间复杂度 O(mn)
    // 空间复杂度 O(1)
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 先比较第一行，第一列是否包含 0
        boolean flagRow1 = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) flagRow1 = true;
        }

        boolean flagCol1 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) flagCol1 = true;
        }

        // 使用 matrix 的第一行和第一列来记录每行每列是否包含 0
        // 第一行，第一列已经标记过了，故直接从 (1,1) 开始标记 0
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // 再次遍历从 (1,1) 位置开始，逐行遍历，将标记为 0 的行和列，设置为 0
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        // 根据标记值，设置第一行，第一列是否为 0
        if (flagRow1) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if (flagCol1) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
