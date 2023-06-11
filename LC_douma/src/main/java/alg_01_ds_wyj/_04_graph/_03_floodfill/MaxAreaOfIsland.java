package alg_01_ds_wyj._04_graph._03_floodfill;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 23:14
 * @Version 1.0
 */
public class MaxAreaOfIsland {
    private int rows;
    private int cols;
    private int[][] grid;
    private Set<Integer>[] graph;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;
        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        // 1 建图
        graph = constructGraph();
        visited = new boolean[graph.length];
        int res = 0;
        for (int v = 0; v < graph.length; v++) {
            int row = v / cols;
            int col = v % cols;
            if (!visited[v] && grid[row][col] == 1) {
                res = Math.max(res, dfs(v));
            }
        }
        return res;
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : graph[v]) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    private Set<Integer>[] constructGraph() {
        Set<Integer>[] graph = new HashSet[rows * cols];
        for (int v = 0; v < graph.length; v++) {
            graph[v] = new HashSet<>();
        }
        for (int v = 0; v < graph.length; v++) {
            int row = v / cols;
            int col = v % cols;
            if (grid[row][col] == 1) {
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if (inArea(nextRow, nextCol)
                            && grid[nextRow][nextCol] == 1) {

                        int w = nextRow * cols + nextCol;
                        graph[v].add(w);
                        graph[w].add(v);
                    }
                }
            }
        }
        return graph;
    }

    private boolean inArea(int row, int col) {
        return (row >= 0 && row < rows && col >= 0 && col < cols);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid)); // 6
    }
}
