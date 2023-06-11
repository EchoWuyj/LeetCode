package alg_01_ds_wyj._04_graph._03_floodfill;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-03-24 0:10
 * @Version 1.0
 */
public class MaxAreaOfIsland2 {
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
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{row, col});
                    grid[row][col] = 0;
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int curRow = cur[0], curCol = cur[1];
                        curOnes++;
                        for (int[] dir : directions) {
                            int nextRow = curRow + dir[0];
                            int nextCol = curCol + dir[1];
                            if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == 1) {
                                stack.push(new int[]{nextRow, nextCol});
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
        MaxAreaOfIsland2 maxAreaOfIsland = new MaxAreaOfIsland2();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
