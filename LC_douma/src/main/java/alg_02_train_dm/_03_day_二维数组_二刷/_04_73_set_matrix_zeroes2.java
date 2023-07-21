package alg_02_train_dm._03_day_二维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 20:40
 * @Version 1.0
 */
public class _04_73_set_matrix_zeroes2 {

    // KeyPoint 方法三 原始矩阵标记 0 所在行和列
    // 使用原始二维数组的第一行，第一列来标记 0 所在行和列
    // 时间复杂度 O(mn)
    // 空间复杂度 O(1)
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 操作逻辑
        // 1.先标记第一行，第一列是否包含 0
        // 2.从第二行，第二列开始逐行标记 0，坐标索引从 (1,1) 开始
        // 3.从第二行，第二列开始逐行设置 0，坐标索引从 (1,1) 开始
        // // 4.根据标记值，设置第一行，第一列是否为 0

        // 1.先标记第一行，第一列是否包含 0
        // 标记第一行是否包含 0
        boolean flagRow = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                flagRow = true;
                break;
            }
        }

        // 标记第一列是否包含 0
        boolean flagCol = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol = true;
                break;
            }
        }

        // 2.从第二行，第二列开始逐行判断矩阵元素是否为 0，
        //   并在原矩阵第一行和第一列上标记 0，坐标索引从 (1,1) 开始
        //   因为第一行，第一列已经标记过了
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    // 通过第一行和第一列标记
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // 3.从第二行，第二列开始逐行设置 0，坐标索引从 (1,1) 开始
        // 将标记为 0 的行和列，设置为 0
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        // 4.根据标记值，设置第一行，第一列是否为 0
        if (flagRow) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if (flagCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
