package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 19:43
 * @Version 1.0
 */

// 二分图检测 => 染色
public class BipartitionDetection {
    private Graph graph;
    // 用于防止一个节点被重复访问
    private boolean[] visited;

    // KeyPoint 染色
    // 记录顶点染色情况:-1 表示没有染颜色，0 表示红色，1 表示蓝色
    private int[] colors;
    // 默认值为 true / false 都是可以的，这里默认是 true
    private boolean isBipartition = true;

    // 在 dfs 遍历中对所有顶点进行染色，染色过程需要判断相邻的顶点颜色是否相同
    public BipartitionDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.colors = new int[graph.getV()];

        // 对 colors 进行初始化为 -1;
        Arrays.fill(colors, -1);

        // 遍历图中每个顶点，对每个连通分量进行 dfs 遍历
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                // KeyPoint 递归函数返回为 boolean 值，外部调用递归函数需要进行判断，不满足直接 break
                // 一开始顶点标记为红色
                if (!dfs(v, 0)) {
                    // 递归结果为 false，则不是二分图，返回 false
                    isBipartition = false;
                    // 退出 for 循环，已经找到不为二分图的情况
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                // 如果 v 的颜色是 1，那么 w 的颜色就是 0
                // 如果 v 的颜色是 0，那么 w 的颜色就是 1
                if (!dfs(w, 1 - color)) return false;

                // 否则，w 顶点已经染过颜色了，此时判断相邻顶点的颜色是否相同
            } else if (colors[w] == colors[v]) {
                // 如果相邻顶点的颜色一样的话，则不是二分图
                return false;
            }
        }
        // KeyPoint 只要有一个为 false 整体为 false，所有的都不为 false，整体才是 true
        //      不能因为有一个 true，就去判断整体为 true
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    public static void main(String[] args) {
        test1(); // true
        test2(); // false
    }

    private static void test2() {
        Graph g = new AdjSet("LC_douma/data/graph-dfs-6.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(g);
        System.out.println(graphDFS.isBipartition()); // false
    }

    private static void test1() {
        Graph g = new AdjSet("LC_douma/data/graph-dfs-5.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(g);
        System.out.println(graphDFS.isBipartition()); // true
    }
}
