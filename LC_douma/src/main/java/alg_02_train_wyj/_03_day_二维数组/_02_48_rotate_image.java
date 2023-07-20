package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 16:21
 * @Version 1.0
 */
public class _02_48_rotate_image {
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] tmpMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmpMatrix[j][n - 1 - i] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = tmpMatrix[i][j];
            }
        }
    }

    public void rotate(int[][] matrix) {
        
    }

    public void rotate2(int[][] matrix) {

    }
}
