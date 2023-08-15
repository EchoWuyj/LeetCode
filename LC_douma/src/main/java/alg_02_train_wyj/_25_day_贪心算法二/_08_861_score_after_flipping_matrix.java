package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 18:39
 * @Version 1.0
 */
public class _08_861_score_after_flipping_matrix {
    public int matrixScore(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] ^= 1;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(grid[i][j]);
            }
            System.out.println();
        }

        int res = 0;
        for (int j = 0; j < cols; j++) {
            int count = 0;
            for (int i = 0; i < rows; i++) {
                count += grid[i][j];
            }
            int maxCount = Math.max(count, rows - count);
            res += maxCount * (1 << (cols - j - 1));
        }
        return res;
    }
}
