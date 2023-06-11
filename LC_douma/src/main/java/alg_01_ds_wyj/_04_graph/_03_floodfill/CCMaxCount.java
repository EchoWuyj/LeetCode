package alg_01_ds_wyj._04_graph._03_floodfill;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 11:11
 * @Version 1.0
 */
public class CCMaxCount {
    private Graph graph;
    private boolean[] visited;
    private int maxVertexNum = 0;

    public CCMaxCount(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                maxVertexNum = Math.max(dfs(v), maxVertexNum);
            }
        }
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    public int getMaxVertexNum() {
        return maxVertexNum;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-7.txt");
        CCMaxCount graphDFS = new CCMaxCount(graph);
        System.out.println(graphDFS.getMaxVertexNum()); // 5
    }
}
