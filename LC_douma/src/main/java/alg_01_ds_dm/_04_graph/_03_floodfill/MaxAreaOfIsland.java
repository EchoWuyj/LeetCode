package alg_01_ds_dm._04_graph._03_floodfill;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 21:17
 * @Version 1.0
 */

// Flood Fill 洪水填充
// KeyPoint 建图 => 不推荐
//  1 数组中的每个元素就是图的顶点
//  2 相邻的且元素值为 1 的两个元素为一个边
public class MaxAreaOfIsland {

    // 提升为成员变量，在不同的方法中进行访问
    private int rows;
    private int cols;

    private int[][] grid;

    // Set 上层接口，使用邻接表来存储图信息
    private Set<Integer>[] graph;

    // 定义四连通
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        // 校验参数
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        // 1. 建图 => 赋值到类中定义的 graph 中
        graph = constructGraph();

        // 2. 求解最大联通分量的顶点数
        this.visited = new boolean[graph.length];
        // 定义结果值
        int res = 0;
        for (int v = 0; v < graph.length; v++) {
            int row = v / cols;
            int col = v % cols;
            // 只是在 grid[row][col] == 1 时，才去进行 dfs，避免不必要的 dfs
            if (!visited[v] && grid[row][col] == 1) {
                res = Math.max(dfs(v), res);
            }
        }
        return res;
    }

    // dfs
    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        // 直接访问 graph[v] 获取相邻顶点，graph 本身就是 Set<Integer>[] 数组
        for (int w : graph[v]) {
            // 相邻的顶点必须是没有被访问过的
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    // 建图:二维数组 => 图
    private Set<Integer>[] constructGraph() {
        // 二维数组中每个元素都是顶点，故所有顶点个数 rows * cols
        // 这里因为数据量不大，所以使用 HashSet 建图，使用 TreeSet也是可以的
        Set<Integer>[] g = new HashSet[rows * cols];

        // 对每个顶点都初始一个 HashSet，存储顶点相邻顶点
        for (int v = 0; v < g.length; v++) {
            g[v] = new HashSet<>();
        }

        // 遍历每个顶点
        for (int v = 0; v < g.length; v++) {
            // 一维转二维
            int row = v / cols;
            int col = v % cols;
            // 相邻的且元素值为 1 的两个元素为一个边
            if (grid[row][col] == 1) {
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    // 相邻顶点没有越界且值为 1
                    if (inArea(nextRow, nextCol)
                            && grid[nextRow][nextCol] == 1) {
                        // 相邻的且元素值为 1 的两个元素为一个边  =>
                        // 将 (nextRow,nextCol) 顶点加入到 v 顶点的相邻的 HashSet 中去
                        // 二维转一维
                        int w = nextRow * cols + nextCol;
                        // 无向边，相互都要添加
                        g[v].add(w);
                        g[w].add(v);
                    }
                }
            }
        }
        return g;
    }

    // 限制区域，不越界方法
    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
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
