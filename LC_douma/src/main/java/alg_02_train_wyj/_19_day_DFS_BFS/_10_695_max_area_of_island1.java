package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 15:52
 * @Version 1.0
 */
public class _10_695_max_area_of_island1 {

    private static boolean[][] visited;
    private static int rows;
    private static int cols;
    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static int res;

    private static int maxAreaOfIsland1(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = 0;
                    dfs1(grid, i, j);
                    int tmp = res;
                    System.out.println("====开始===");
                    System.out.println("tmp = " + tmp);
                    res = Math.max(tmp, res);
                    // 通过 输出打印 进行 debug 调试
                    System.out.println("res = " + res);
                    System.out.println("====结束====");

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
                    dfs1(grid, i, j);
                    MaxArea = Math.max(res, MaxArea);
                    System.out.println("MaxArea = " + MaxArea);
                }
            }
        }
        return MaxArea;
    }

    public static void dfs1(int[][] grid, int i, int j) {
        if (!inArea(i, j) || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        res++;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs1(grid, nexti, nextj);
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
