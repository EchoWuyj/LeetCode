package alg_01_ds_wyj._04_graph._02_bfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 19:30
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
                if (!bfs(v)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    public boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.offer(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[cur];
                } else {
                    if (colors[w] == colors[cur]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        BipartitionDetection graphBFS = new BipartitionDetection(graph);
        System.out.println(graphBFS.isBipartition()); // true;
    }
}
