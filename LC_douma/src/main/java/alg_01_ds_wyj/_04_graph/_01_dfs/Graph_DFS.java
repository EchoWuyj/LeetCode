package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 15:09
 * @Version 1.0
 */
public class Graph_DFS {

    private Graph graph;
    private List<Integer> res;
    private boolean[] visited;

    public Graph_DFS(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.res = new ArrayList<>();
        this.visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    public void dfs(int v) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(v);
        visited[v] = true;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            res.add(cur);
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-1.txt");
        Graph_DFS graph_dfs = new Graph_DFS(graph);
        System.out.println(graph_dfs.getRes()); // [0, 2, 6, 5, 3, 1, 4]
    }
}
