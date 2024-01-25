package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:22
 * @Version 1.0
 */
public class _23_54_spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 顺时针方向
        // 右 下 左 上
        // y  x  y  x
        // 前两个对称，后两个对称
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length;
        int n = matrix[0].length;
        int dir = 0;
        int row = 0, col = 0;
        List<Integer> res = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m * n; i++) {
            // 加入结果集，并且设置为 true
            res.add(matrix[row][col]);
            visited[row][col] = true;

            // 判断下个位置是否越界
            int nextRow = row + dirs[dir][0];
            int nextCol = col + dirs[dir][1];

            if (nextRow < 0 || nextRow >= m
                    || nextCol < 0 || nextCol >= n
                    || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
            }

            // 实际下个位置，对 (row,col) 进行覆盖
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];
        }
        return res;
    }
}
