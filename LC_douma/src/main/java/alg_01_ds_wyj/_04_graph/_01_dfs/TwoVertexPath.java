package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 13:00
 * @Version 1.0
 */
public class TwoVertexPath {
    private Graph graph;
    private int source;
    private int target;

    private boolean[] visited;
    private int[] prevs;
    private List<Integer> res;

    public TwoVertexPath(Graph graph, int source, int target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.res = new ArrayList<>();
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];

        // 初始化
        for (int v = 0; v < graph.getV(); v++) {
            prevs[v] = -1;
        }
        dfs(source, source);

        path();
    }

    private List<Integer> path() {
        if (!isConnected()) {
            return res;
        }
        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public boolean dfs(int v, int prev) {
        visited[v] = true;
        prevs[v] = prev;
        if (v == target) return true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        TwoVertexPath graphDFS = new TwoVertexPath(graph, 0, 6);
        System.out.println(graphDFS.getRes()); // [0, 1, 3, 6]
    }
}
