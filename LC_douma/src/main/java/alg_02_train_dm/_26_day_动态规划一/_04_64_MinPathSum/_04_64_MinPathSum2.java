package alg_02_train_dm._26_day_动态规划一._04_64_MinPathSum;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:02
 * @Version 1.0
 */
public class _04_64_MinPathSum2 {
    private int[][] dirs = {{1, 0}, {0, 1}};

    public int minPathSum(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    // KeyPoint 另外一种回溯，dfs 有返回值
    // 遍历每个节点，计算该节点 (i,j) 到右下角(终点)最小路径和
    private int dfs(int[][] grid, int row, int col) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            // 终点直接返回该节点路径值
            // 抽象树形结构，最终的叶子节点都是右下角(终点)，此时将 grid[row][col]返回
            return grid[row][col];
        }
        int minPathSum = Integer.MAX_VALUE;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextCol < 0
                    || nextRow >= grid.length
                    || nextCol >= grid[0].length) continue;
            int childMinPathSum = dfs(grid, nextRow, nextCol);
            // Math.min 代码结束后，还有外层的 for 循环，并不是直接返回递归上层
            // for 循环遍历所有子节点，获取子节点路径值，比较从而获取 minPathSum
            minPathSum = Math.min(minPathSum, childMinPathSum);
        }
        // 该节点路径 + Min{子节点路径} => 该节点路径最小值
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
