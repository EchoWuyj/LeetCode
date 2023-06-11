package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 16:42
 * @Version 1.0
 */
public class _06_59_spiral_matrix_ii {
    public int[][] generateMatrix1(int n) {
        int[][] res = new int[n][n];
        int dir = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[n][n];
        int col = 0, row = 0;
        int num = 1;
        for (int i = 0; i < n * n; i++) {
            res[row][col] = num++;
            visited[row][col] = true;
            int nextRow = row + dirs[dir][0];
            int nextCol = col + dirs[dir][1];

            if (nextRow < 0 || nextRow >= n
                    || nextCol < 0 || nextCol >= n
                    || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
            }
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        int num = 1;
        int[][] res = new int[n][n];
        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int col = startCol; col <= endCol; col++) res[startRow][col] = num++;
            for (int row = startRow + 1; row <= endRow; row++) res[row][endCol] = num++;
            if (startRow < endRow && startCol < endCol) {
                for (int col = endCol - 1; col > startCol; col--) res[endRow][col] = num++;
                for (int row = endRow; row > startRow; row--) res[row][startCol] = num++;
            }

            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
}
