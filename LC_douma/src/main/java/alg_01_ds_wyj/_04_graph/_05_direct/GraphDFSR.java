package alg_01_ds_wyj._04_graph._05_direct;

import alg_01_ds_wyj._04_graph.Graph;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 23:42
 * @Version 1.0
 */
public class GraphDFSR {
    private Graph g;
    private List<Integer> res;
    private boolean[] visited;

    public GraphDFSR(Graph graph) {
        this.g = graph;
        if (g == null) return;
        this.res = new ArrayList<>();
        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    public void dfs(int v) {
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
        // 有向图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs.txt", true);
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes()); // [0, 1, 3, 5, 6, 4, 2]
    }
}
