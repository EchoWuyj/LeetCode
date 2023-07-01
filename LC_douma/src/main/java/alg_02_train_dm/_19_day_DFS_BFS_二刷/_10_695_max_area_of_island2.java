package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 16:13
 * @Version 1.0
 */
public class _10_695_max_area_of_island2 {

    private static boolean[][] visited;
    private static int rows;
    private static int cols;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static int res;

    private static int maxAreaOfIsland1(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        // 类中方法外，只是定义成员变量，在主函数中赋初值
        res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    // 重置 res
                    res = 0;
                    // 执行 dfs，res 更新
                    dfs(grid, i, j);
                    // KeyPoint bug 原因
                    // tmp 记录 res，此时 res 和 tmp 一样大
                    // Math.max(tmp, res) 没有意义，没有起到记录最大值作用
                    // 最后 res 返回的是最后一轮 dfs 的 res 的值
                    // => 不要盲目相信 ChatGPT，有时候解决 bug 也不是很行
                    int tmp = res;

                    // 遇到 bug，通过 输出打印 进行 debug 调试
                    // 而不是盲目去找 bug，一步一步缩小 bug 位置 => 不要死脑筋，及时转换方向
                    System.out.println("==== start ===");
                    System.out.println("tmp = " + tmp);
                    res = Math.max(tmp, res);
                    System.out.println("res = " + res);
                    System.out.println("==== end ====");

                    // 换行作用
                    System.out.println();
                }
            }
        }
        return res;
    }

    private static int maxAreaOfIsland2(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        res = 0;
        int MaxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = 0;
                    dfs(grid, i, j);
                    // 重新定义 MaxArea，每次 res 和 MaxArea 比较，取较大值
                    MaxArea = Math.max(res, MaxArea);
                }
            }
        }
        return MaxArea;
    }

    // dfs 前序遍历
    public static void dfs(int[][] grid, int i, int j) {
        if (!inArea(i, j) || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        res++;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(grid, nexti, nextj);
        }
    }

    public static boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1
                , 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        System.out.println(maxAreaOfIsland2(arr));
    }
}
