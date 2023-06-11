package alg_01_ds_wyj._04_graph._01_dfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.beans.beancontext.BeanContext;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 15:11
 * @Version 1.0
 */
public class BipartitionDetection {
    private Graph graph;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartitionDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.colors = new int[graph.getV()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else {
                if (colors[w] == colors[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        Graph g = new AdjSet("LC_douma/data/graph-dfs-6.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(g);
        System.out.println(graphDFS.isBipartition()); // false
    }

    private static void test1() {
        Graph g = new AdjSet("LC_douma/data/graph-dfs-5.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(g);
        System.out.println(graphDFS.isBipartition()); // true
    }
}
