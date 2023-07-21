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
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0, j = 0;
        int dir = 0;
        List<Integer> res = new ArrayList<>();
        boolean[][] seen = new boolean[m][n];

        for (int count = 0; count < m * n; count++) {
            res.add(matrix[i][j]);
            seen[i][j] = true;

            int nexti = i + dirs[dir][0];
            int nextj = j + dirs[dir][1];

            if (nexti < 0 || nexti >= m ||
                    nextj < 0 || nextj >= n || seen[nexti][nextj]) {
                dir = (dir + 1) % 4;
            }

            i = i + dirs[dir][0];
            j = j + dirs[dir][1];
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
