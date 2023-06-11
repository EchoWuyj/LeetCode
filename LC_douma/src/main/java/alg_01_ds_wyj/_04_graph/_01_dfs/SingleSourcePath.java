package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 11:54
 * @Version 1.0
 */
public class SingleSourcePath {
    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] prevs;

    public SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            prevs[v] = -1;
        }
        dfs(source, source);
    }

    public void dfs(int v, int prev) {
        visited[v] = true;
        prevs[v] = prev;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }

        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(graph, 0);
        System.out.println(graphDFS.path(6)); // [0, 1, 3, 6]
    }
}
