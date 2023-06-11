package alg_01_ds_wyj._04_graph._05_direct;

import alg_01_ds_wyj._04_graph.Graph;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 21:10
 * @Version 1.0
 */
public class TopologySortDFS {
    private Graph g;
    private boolean hasCycle = false;
    private boolean[] visited;
    private boolean[] isOnPath;
    private int[] res;
    private int index;

    public TopologySortDFS(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        this.res = new int[g.getV()];
        this.index = this.res.length - 1;
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean dfs(int v) {
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
        res[index--] = v;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }

    public static void main(String[] args) {
        test1();
        System.out.println("==============");
        test2();
    }

    private static void test2() {
        // 有环
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-2.txt", true);
        TopologySortDFS graphDFS = new TopologySortDFS(g);
        System.out.println(graphDFS.isHasCycle()); // true
        System.out.println(Arrays.toString(graphDFS.getRes())); // [0, 0, 0, 2, 4]
    }

    private static void test1() {
        // 无环
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-1.txt", true);
        TopologySortDFS graphDFS = new TopologySortDFS(g);
        System.out.println(graphDFS.isHasCycle()); // false
        System.out.println(Arrays.toString(graphDFS.getRes())); // [0, 1, 3, 2, 4]
    }
}
