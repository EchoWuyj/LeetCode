package alg_03_high_frequency._01_codetop.top_100;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:22
 * @Version 1.0
 */
public class _23_54_spiralOrder {

    // 螺旋矩阵
    // 直接模拟
    public List<Integer> spiralOrder(int[][] matrix) {

        // 顺时针方向：右 →下 → 左 → 上
        //  {0,1} | {1,0}  {0,-1} | {-1,0}
        //   右   →  下  →   左   →   上
        // 前两个对称，后两个对称
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0;

        int m = matrix.length;
        int n = matrix[0].length;
        // 当前坐标
        int row = 0, col = 0;

        boolean[][] visited = new boolean[m][n];
        List<Integer> res = new ArrayList<>();

        // 有多少个元素，则执行循环多少次
        for (int i = 0; i < m * n; i++) {
            // 加入结果集，并且设置为 true
            res.add(matrix[row][col]);
            visited[row][col] = true;

            // 计算下个位置[可能的]索引坐标
            int nextRow = row + dirs[dir][0];
            int nextCol = col + dirs[dir][1];

            // 判断下个位置是否越界以及是否访问过
            // 若越界 或者 已经访问过，则修改 dir
            if (nextRow < 0 || nextRow >= m
                    || nextCol < 0 || nextCol >= n
                    || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
            }

            // 真正的下个位置，对 (row,col) 进行覆盖
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];
        }
        return res;
    }
}
