package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 19:35
 * @Version 1.0
 */
public class _07_498_diagonal_traverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] dirs = {{-1, 1}, {1, -1}};
        int i = 0, j = 0;
        int dir = 0;
        int[] res = new int[m * n];

        for (int count = 0; count < m * n; count++) {
            res[count] = mat[i][j];

            i = i + dirs[dir][0];
            j = j + dirs[dir][1];

            if (j >= n) {
                i += 2;
                j = n - 1;
                dir = 1 - dir;
            }

            if (i >= m) {
                j += 2;
                i = m - 1;
                dir = 1 - dir;
            }

            if (i < 0) {
                i = 0;
                dir = 1 - dir;
            }

            if (j < 0) {
                j = 0;
                dir = 1 - dir;
            }
        }
        return res;
    }
}
