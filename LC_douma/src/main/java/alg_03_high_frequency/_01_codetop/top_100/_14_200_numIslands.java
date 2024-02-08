package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 23:36
 * @Version 1.0
 */
public class _14_200_numIslands {

    // 辅助 数据结构
    // 遍历的 4 个方向
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    // 标记 i 和 j 位置，元素是否被访问
    private boolean[][] visited;

    // 自身 数据结构
    private char[][] grid;
    private int rows;
    private int cols;

    private int res;

    // 岛屿数量
    // 深度优先遍历
    public int numIslands(char[][] grid) {

        // 若是拆分定义，需要加上一个 new int[][]
        // dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        visited = new boolean[rows][cols];

        // 初始化
        this.grid = grid;

        // KeyPoint 易错点
        // 不能再去定义 int rows 和 int cols，得使用全局的 rows 和 cols
        // 否则本地的 rows 和 cols 覆盖全局的 rows 和 cols，导致后续的代码出现 bug
        rows = grid.length;
        cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(int i, int j) {
        // 三个边界条件，其中一个不满足，直接 return
        if (!inArea(i, j) || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        // 设置已经访问
        visited[i][j] = true;

        for (int[] dir : dirs) {
            // 下一个位置
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            // 继续 dfs
            dfs(nexti, nextj);
        }
    }

    // 返回值为 boolean
    public boolean inArea(int i, int j) {
        // 判断逻辑和 inArea 函数名保持一致，索引 (i,j) 在区域内
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
