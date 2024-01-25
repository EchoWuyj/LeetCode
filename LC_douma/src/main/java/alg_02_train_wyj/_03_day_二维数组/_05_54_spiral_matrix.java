package alg_02_train_wyj._03_day_二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 14:57
 * @Version 1.0
 */
public class _05_54_spiral_matrix {
    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0, col = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m * n; i++) {
            res.add(matrix[row][col]);
            visited[row][col] = true;

            int nextRow = row + dirs[dir][0];
            int nextCol = col + dirs[dir][1];

            if (nextRow < 0 || nextRow >= m || nextCol < 0
                    || nextCol >= n || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
            }
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        int startRow = 0, endRow = m - 1;
        int startCol = 0, endCol = n - 1;

        while (startRow <= endRow && startCol <= endCol) {
            for (int j = startCol; j <= endCol; j++) res.add(matrix[startRow][j]);
            for (int i = startRow + 1; i <= endRow; i++) res.add(matrix[i][endCol]);
            if (startCol < endCol && startRow < endRow) {
                for (int j = endCol - 1; j >= startCol; j--) res.add(matrix[endRow][j]);
                for (int i = endRow - 1; i >= startRow + 1; i--) res.add(matrix[i][startCol]);
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
}
