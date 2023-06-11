package alg_01_ds_wyj._04_graph._03_floodfill;

import com.sun.imageio.plugins.gif.GIFImageReader;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-24 11:50
 * @Version 1.0
 */
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
                    int curOnes = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{row, col});
                    grid[row][col] = 0;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int curRow = cur[0], curCol = cur[1];
                        curOnes++;
                        for (int[] dir : directions) {
                            int nextRow = curRow + dir[0];
                            int nextCol = curCol + dir[1];
                            if (inArea(nextRow, nextCol)
                                    && grid[nextRow][nextCol] == 1) {
                                queue.offer(new int[]{nextRow, nextCol});
                                grid[nextRow][nextCol] = 0;
                            }
                        }
                    }
                    res = Math.max(res, curOnes);
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
