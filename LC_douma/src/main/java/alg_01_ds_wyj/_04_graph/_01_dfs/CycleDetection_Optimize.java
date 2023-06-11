package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 15:05
 * @Version 1.0
 */
public class CycleDetection_Optimize {
    private Graph graph;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection_Optimize(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean dfs(int v, int prev) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            } else {
                if (w != prev) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-4.txt");
        CycleDetection_Optimize graphDFS = new CycleDetection_Optimize(graph);
        System.out.println(graphDFS.hasCycle()); // false;
    }

    private static void test1() {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        CycleDetection_Optimize graphDFS = new CycleDetection_Optimize(graph);
        System.out.println(graphDFS.hasCycle()); // true
    }
}
