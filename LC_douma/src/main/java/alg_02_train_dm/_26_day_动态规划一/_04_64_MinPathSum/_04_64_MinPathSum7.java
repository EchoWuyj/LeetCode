package alg_02_train_dm._26_day_动态规划一._04_64_MinPathSum;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:05
 * @Version 1.0
 */
public class _04_64_MinPathSum7 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 压缩状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) {
                    // dp 数组和 grid 数组规格一样，grid 元素值可以被修改，故使用 grid 代替 dp
                    // 代码修改：直接使用 grid 代替 dp，其余部分代码不用改变
                    // 边计算 dp[i][j]，边使用 dp[i][j] 覆盖 grid[i][j]，从而达到节省空间目的
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i != 0 && j != 0) {
                    grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new _04_64_MinPathSum7().minPathSum(grid));
    }
}
