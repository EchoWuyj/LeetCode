package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 11:16
 * @Version 1.0
 */
public class _04_73_set_matrix_zeroes {
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
    // 时间复杂度 O(mn)
    // 空间复杂度 O(m+n)
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 遍历原始二维数组过程中，决定那一行，那一列设置为 0
        // 可以使用两个数组来标记，记录每行或者每列是否包含 0
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
                if (rows[row] || cols[col]) {
                    matrix[row][col] = 0;
                }
            }
        }
    }

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

    // KeyPoint 方法三 优化：减少标记变量，for 循环次数
    // 虽然方法二和方法三的时间复杂度都是 O(mn) ，但是方法三减少 for 循环次数，还是能提高算法性能
    // 时间复杂度 O(mn)
    // 空间复杂度 O(1)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 标记第一列是否包含 0
        boolean flagCol1 = false;

        // 使用 matrix 的第一行，第一列来记录每行每列是否包含 0
        for (int row = 0; row < m; row++) {
            // 判断每行第一列是否包含 0，标记 flagCol1
            if (matrix[row][0] == 0) flagCol1 = true;
            // 判断每行第二列是否包含 0，标记 matrix[0][col] 和 matrix[row][0]
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // 为了防止每一列第一个元素(第一行元素)(标记元素)被提前更新，从最后一行开始，倒序地处理矩阵元素
        for (int row = m - 1; row >= 0; row--) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
            // 每遍历一行，将每行开头元素设置为 0
            if (flagCol1) {
                matrix[row][0] = 0;
            }
        }
    }
}
