package alg_02_train_dm._03_day_二维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 20:40
 * @Version 1.0
 */
public class _04_73_set_matrix_zeroes3 {

    // KeyPoint 方法四 优化：减少标记变量，for 循环次数
    // 注意：虽然方法二和方法三的时间复杂度都是 O(mn)，但是方法三减少 for 循环次数，还是能提高算法性能
    // 时间复杂度 O(mn)
    // 空间复杂度 O(1)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 标记第一列是否包含 0
        boolean flagCol = false;

        // 使用 matrix 的第一行，第一列来记录每行每列是否包含 0
        for (int row = 0; row < m; row++) {
            // KeyPoint 合并 for 循环
            // 判断每行第一列是否包含 0，标记 flagCol
            // => 将其并入 for 循环中，避免单独 for 循环
            if (matrix[row][0] == 0) flagCol = true;
            // 判断每行第二列是否包含 0，标记 matrix[0][col] 和 matrix[row][0]
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // 为了防止矩阵第一行非零元素被提前设置为 0，而被设置成的 0 会影响后续标记 0
        // => 故从最后一行开始，倒序地处理矩阵元素
        // KeyPoint 注意：row 和 m 对应，row 和 n 对应，两者不要搞混淆了
        for (int row = m - 1; row >= 0; row--) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
            // KeyPoint 合并 for 循环
            // 每遍历一行，将每行开头元素设置为 0
            if (flagCol) {
                matrix[row][0] = 0;
            }
        }
    }
}
