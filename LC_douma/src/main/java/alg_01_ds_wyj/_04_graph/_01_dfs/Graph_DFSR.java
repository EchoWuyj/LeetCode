package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 15:09
 * @Version 1.0
 */
public class Graph_DFSR {
    private Graph graph;
    private List<Integer> res;
    private boolean[] visited;

    public Graph_DFSR(Graph graph) {
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

    private void dfs(int v) {
        res.add(v);
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
                visited[w] = true;
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-1.txt");
        Graph_DFSR graph_DFS = new Graph_DFSR(graph);
        System.out.println(graph_DFS.getRes()); // [0, 1, 3, 2, 6, 5, 4]
    }
}
