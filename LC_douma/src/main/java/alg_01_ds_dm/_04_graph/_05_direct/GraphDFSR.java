package alg_01_ds_dm._04_graph._05_direct;

import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 14:43
 * @Version 1.0
 */

// KeyPoint 有向图无权图 深度优先遍历 => 代码逻辑不变
public class GraphDFSR {
    private Graph g;

    private List<Integer> res;
    // 用于防止一个节点被重复访问
    private boolean[] visited;

    public GraphDFSR(Graph g) {
        this.g = g;
        if (g == null) return;
        this.res = new ArrayList<>();
        this.visited = new boolean[g.getV()];

        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        res.add(v);
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        // 无向图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs.txt", false);
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes()); // [0, 1, 3, 2, 6, 5, 4]
    }

    private static void test2() {
        // 有向图，因为是有向图，限制了方向，所以遍历结果有所不同
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs.txt", true);
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes()); // [0, 1, 3, 5, 6, 4, 2]
    }
}
