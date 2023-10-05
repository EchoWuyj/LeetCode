package alg_02_train_dm._26_day_动态规划一_二刷._04_64_MinPathSum;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:02
 * @Version 1.0
 */
public class _04_64_MinPathSum1 {

    /*
        64. 最小路径和 => 最优值问题 => 动态规划

        给定一个包含 非负整数 的 m x n 网格 grid ，
        请找出一条从 左上角 到 右下角 的路径，使得路径上的数字总和为最小。
        说明：每次只能向下或者向右移动一步。

        输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
        输出：7
        解释：因为路径 1→3→1→1→1 的总和最小。

        提示：
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 200
        0 <= grid[i][j] <= 200

        KeyPoint 判断是否超时
        O(n^3) => 200 ~ 500
        O(2^n) => 20 ~ 24
        => 根据 1 <= m, n <= 200，
        => 该 O(2^(m+n)) 必然超时，所以本题不能使用回溯来解决，将该方法排除
     */

    private int minPathSum = Integer.MAX_VALUE;
    // x=2 -> x'=3 向下移动
    // y=2 -> y'=3 向右移动
    private int[][] dirs = {{1, 0}, {0, 1}};

    public int minPathSum(int[][] grid) {
        dfs(grid, 0, 0, grid[0][0]);
        return minPathSum;
    }

    // KeyPoint 补充说明：回溯和图 DFS 代码不太一样，注意两者之间的区别
    // DFS 前序遍历 => 超时
    // 时间复杂度 O(2^(m+n))
    // 对于 m 行 n 列的网格，我们需要进行 m + n 步才能从起点移动到终点，每次移动有 2 个可能的方向可以选择
    private void dfs(int[][] grid, int row, int col, int sum) {

        // 右下角为终点，即为递归边界
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            // 遍历到终点位置，才开始计算 minPathSum
            minPathSum = Math.min(minPathSum, sum);
            // 结束方法
            return;
        }

        // 每次只能向下 或者 向右移动一步 => 树形结构(二叉树)
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 剪枝操作：通过检查网格的边界进行剪枝，避免了对超出边界的位置
            //           剪枝操作并不改变整体的指数级时间复杂度
            if (nextRow >= grid.length
                    || nextCol >= grid[0].length) continue;
            sum += grid[nextRow][nextCol];
            dfs(grid, nextRow, nextCol, sum);
            // 回溯，返回上一层，恢复现场
            sum -= grid[nextRow][nextCol];
        }
    }
}
