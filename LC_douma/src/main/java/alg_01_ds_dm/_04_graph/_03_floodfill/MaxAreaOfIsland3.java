package alg_01_ds_dm._04_graph._03_floodfill;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 12:45
 * @Version 1.0
 */
// KeyPoint 思路:bfs => 优先找相邻的顶点(只要相邻且没有被访问则加入队列中)
//               => 图的某个顶点开始，先访问它的所有邻居，再访问邻居的邻居
//               => 可以类比树，抽象出层的概念
public class MaxAreaOfIsland3 {
    private int rows;
    private int cols;

    private int[][] grid;

    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        int res = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    int currOnes = 0;
                    // KeyPoint bfs 和 dfs 迭代方式，只需要修改数据结构即可，代码逻辑是一样的
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{row, col});
                    // bug 修复，每次将元素 push 到栈中都要将其设置为 0
                    grid[row][col] = 0;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.remove();
                        int currRow = curr[0], currCol = curr[1];
                        currOnes++;
                        // 上下左右寻找值为 1 的顶点
                        for (int[] dir : directions) {
                            int nextRow = currRow + dir[0];
                            int nextCol = currCol + dir[1];
                            if (inArea(nextRow, nextCol)
                                    && grid[nextRow][nextCol] == 1) {
                                queue.add(new int[]{nextRow, nextCol});
                                // bug 修复，每次将元素 push 到栈中都要将其设置为 0
                                grid[nextRow][nextCol] = 0;
                            }
                        }
                    }
                    res = Math.max(res, currOnes);
                }
            }
        }
        return res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIsland3 maxAreaOfIsland = new MaxAreaOfIsland3();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
