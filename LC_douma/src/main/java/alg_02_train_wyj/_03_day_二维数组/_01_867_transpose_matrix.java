package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 14:18
 * @Version 1.0
 */
public class _01_867_transpose_matrix {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] tmp = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[j][i] = matrix[i][j];
            }
        }
        return tmp;
    }
}
