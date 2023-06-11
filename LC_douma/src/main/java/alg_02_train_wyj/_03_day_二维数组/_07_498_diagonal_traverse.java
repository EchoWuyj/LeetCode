package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 19:35
 * @Version 1.0
 */
public class _07_498_diagonal_traverse {
    public int[] findDiagonalOrder(int[][] mat ) {
        int m = mat .length;
        int n = mat [0].length;
        int[][] dirs = {{-1, 1}, {1, -1}};
        int dir = 0;
        int row = 0, col = 0;
        int[] res = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            res[i] = mat [row][col];
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];

            if (col >= n) {
                col = n - 1;
                row += 2;
                dir = 1 - dir;
            }

            if (row >= m) {
                row = m - 1;
                col += 2;
                dir = 1 - dir;
            }

            if (col < 0) {
                col = 0;
                dir = 1 - dir;
            }

            if (row < 0) {
                row = 0;
                dir = 1 - dir;
            }
        }
        return res;
    }
}
