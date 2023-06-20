package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 15:37
 * @Version 1.0
 */
public class _04_64_MinPathSum1 {

    private int minPathSum = Integer.MAX_VALUE;
    private int[][] dirs = {{1, 0}, {0, 1}};

    public int minPathSum(int[][] grid) {
        dfs(grid, 0, 0, grid[0][0]);
        return minPathSum;
    }

    public void dfs(int[][] grid, int i, int j, int pathSum) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            minPathSum = Math.min(minPathSum, pathSum);
        }

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (nexti == grid.length || nextj == grid[0].length) continue;
            pathSum += grid[nexti][nextj];
            dfs(grid, nexti, nextj, pathSum);
            pathSum -= grid[nexti][nextj];
        }
    }
}
