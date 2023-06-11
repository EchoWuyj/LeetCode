package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 10:57
 * @Version 1.0
 */
public class CC_Vertex {
    private Graph graph;
    private int[] visited;
    private int ccCount = 0;

    public CC_Vertex(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                dfs(v, ccCount);
            }
        }
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public void dfs(int v, int ccID) {
        visited[v] = ccID;
        for (int w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccID);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    // 连通分量
    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];
        // 初始化 res
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < graph.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);
        }
        return res;
    }

    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return visited[v] == visited[w];
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-2.txt");
        CC_Vertex graphDFS = new CC_Vertex(graph);
        System.out.println(graphDFS.getCcCount()); // 2
        System.out.println(Arrays.toString(graphDFS.components())); // [[0, 1, 2, 3, 4], [5, 6]]
        System.out.println(graphDFS.isConnected(0, 6)); // false
    }
}
