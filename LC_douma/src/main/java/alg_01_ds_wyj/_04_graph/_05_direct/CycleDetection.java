package alg_01_ds_wyj._04_graph._05_direct;

import alg_01_ds_wyj._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 20:46
 * @Version 1.0
 */
public class CycleDetection {
    private Graph g;
    private boolean hasCycle = false;
    private boolean[] visited;
    private boolean[] isOnPath;

    public CycleDetection(Graph g) {
        this.g = g;
        if (g == null) return;
        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v) {
        visited[v] = true;
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else {
                if (isOnPath[w]) return true;
            }
        }
        isOnPath[v] = false;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        test1(); // false
        test2(); // true
    }

    private static void test2() {
        // 有环图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-2.txt", true);
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.isHasCycle()); // true
    }

    private static void test1() {
        // 无环图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-1.txt", true);
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.isHasCycle()); // false
    }


}
