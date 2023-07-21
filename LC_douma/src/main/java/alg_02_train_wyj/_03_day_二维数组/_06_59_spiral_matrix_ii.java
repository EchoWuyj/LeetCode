package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 16:42
 * @Version 1.0
 */
public class _06_59_spiral_matrix_ii {
    public int[][] generateMatrix1(int n) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int i = 0, j = 0;
        int[][] res = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int dir = 0;
        for (int count = 0; count < n * n; count++) {
            res[i][j] = count + 1;
            visited[i][j] = true;

            int nexti = i + dirs[dir][0];
            int nextj = j + dirs[dir][1];

            if (nexti < 0 || nexti >= n || nextj < 0 || nextj >= n || visited[nexti][nextj]) {
                dir = (dir + 1) % 4;
            }
            i = i + dirs[dir][0];
            j = j + dirs[dir][1];
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int starti = 0, endi = n - 1;
        int startj = 0, endj = n - 1;
        int num = 1;
        while (starti <= endi && startj <= endj) {
            for (int j = startj; j <= endj; j++) res[starti][j] = num++;
            for (int i = starti + 1; i <= endi; i++) res[i][endj] = num++;
            if (starti < endi && startj < endj) {
                for (int j = endj - 1; j >= startj; j--) res[endi][j] = num++;
                for (int i = endi - 1; i >= starti + 1; i--) res[i][startj] = num++;
            }
            starti++;
            startj++;
            endi--;
            endj--;
        }
        return res;
    }
}
