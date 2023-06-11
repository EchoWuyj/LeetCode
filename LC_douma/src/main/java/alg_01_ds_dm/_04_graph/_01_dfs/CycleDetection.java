package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 19:05
 * @Version 1.0
 */
// 环检测
public class CycleDetection {
    private Graph graph;
    // 用于防止一个节点被重复访问
    private boolean[] visited;
    // 在 dfs 中修改 hasCycle 的值
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    // KeyPoint 该代码的缺点，即不管是否已经前提检测到到环，都会将所有的顶点遍历一遍，效率不高
    private void dfs(int v, int prev) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            } else {
                // 否则，w 顶点已经被访问，且 w 不是 v 的前一个节点的话，那么就存在环
                if (w != prev) {
                    // 通过变量 hasCycle 控制你是否有环
                    hasCycle = true;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        // 将 1-3 这条条边去除掉，原来图中的环就已经没有了
        Graph g = new AdjSet("LC_douma/data/graph-dfs-4.txt");
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.hasCycle()); // false
    }

    private static void test1() {
        Graph g = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.hasCycle()); // true
    }
}
