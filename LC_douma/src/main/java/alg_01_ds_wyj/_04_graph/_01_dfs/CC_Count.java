package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 10:47
 * @Version 1.0
 */

public class CC_Count {
    private Graph graph;
    private boolean[] visited;
    private int ccCount = 0;

    public CC_Count(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                ccCount++;
                dfs(v);
            }
        }
    }

    public void dfs(int v) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-2.txt");
        CC_Count graphDFS = new CC_Count(graph);
        System.out.println(graphDFS.getCcCount()); // 2
    }
}
