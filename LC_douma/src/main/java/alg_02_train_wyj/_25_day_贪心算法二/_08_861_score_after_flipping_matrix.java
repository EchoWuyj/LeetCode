package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 18:39
 * @Version 1.0
 */
public class _08_861_score_after_flipping_matrix {
    public int matrixScore(int[][] A) {
        int rows = A.length, cols = A[0].length;
        for (int row = 0; row < rows; row++) {
            if (A[row][0] == 0) {
                for (int col = 0; col < cols; col++) {
                    A[row][col] ^= 1;
                }
            }
        }

        int res = 0;
        for (int col = 0; col < cols; col++) {
            int count = 0;
            for (int row = 0; row < rows; row++) {
                count += A[row][col];
            }
            int maxCount = Math.max(count, rows - count);
            res += maxCount * (1 << (cols - col - 1));
        }

        return res;
    }
}
