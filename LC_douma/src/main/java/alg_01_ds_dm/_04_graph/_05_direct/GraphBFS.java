package alg_01_ds_dm._04_graph._05_direct;

import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 14:44
 * @Version 1.0
 */

// KeyPoint 有向图无权图 广度优先遍历 => 代码逻辑不变
public class GraphBFS {
    private Graph g;
    private boolean[] visited;
    private List<Integer> res;

    public GraphBFS(Graph g) {
        this.g = g;
        if (g == null) return;
        this.visited = new boolean[g.getV()];
        this.res = new ArrayList<>();
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) bfs(v);
        }
    }

    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        while (!queue.isEmpty()) {
            int curVer = queue.poll();
            res.add(curVer);
            for (int w : g.adj(curVer)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
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
        // 无向无权图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs.txt", false);
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println(graphBFS.getRes()); // [0, 1, 2, 3, 4, 6, 5]
    }

    private static void test2() {
        // 有向无权图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs.txt", true);
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println(graphBFS.getRes()); // [0, 1, 2, 3, 4, 6, 5]
    }
}
