package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 16:50
 * @Version 1.0
 */
public class _04_64_MinPathSum2 {
    int[][] dirs = {{0, 1}, {1, 0}};

    public int minPathSum(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    private int dfs(int[][] grid, int row, int col) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }

        int minPathSum = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextRow >= grid.length
                    || nextCol < 0 || nextCol >= grid[0].length) continue;
            int childMinPathSum = dfs(grid, nextRow, nextCol);
            minPathSum = Math.min(childMinPathSum, minPathSum);
        }
        return minPathSum + grid[row][col];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new _04_64_MinPathSum2().minPathSum(grid));
    }
}
