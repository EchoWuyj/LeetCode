package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;
import com.sun.javafx.scene.text.HitInfo;

import java.util.GregorianCalendar;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 14:47
 * @Version 1.0
 */
public class CycleDetection {
    private Graph graph;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int prev) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            } else {
                if (w != prev) {
                    hasCycle = true;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        test1(); // true
        test2(); // false
    }

    private static void test2() {
        // 将 1-3 这条条边去除掉，原来图中的环就已经没有了
        Graph g = new AdjSet("LC_douma/data/graph-dfs-4.txt");
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.hasCycle()); // false
    }

    private static void test1() {
        Graph g = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.hasCycle()); // true
    }
}
