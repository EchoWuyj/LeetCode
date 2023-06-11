package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 15:37
 * @Version 1.0
 */
public class _04_64_MinPathSum1 {
    private int minPathSum = Integer.MAX_VALUE;
    private int[][] dirs = {{0, 1}, {1, 0}};

    public int minPathSum(int[][] grid) {
        dfs(grid, 0, 0, grid[0][0]);
        return minPathSum;
    }

    private void dfs(int[][] grid, int row, int col, int sum) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            minPathSum = Math.min(minPathSum, sum);
            return;
        }
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextRow >= grid.length
                    || nextCol < 0 || nextCol >= grid[0].length) {
                continue;
            }
            sum += grid[nextRow][nextCol];
            dfs(grid, nextRow, nextCol, sum);
            sum -= grid[nextRow][nextCol];
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new _04_64_MinPathSum1().minPathSum(grid));
    }
}
