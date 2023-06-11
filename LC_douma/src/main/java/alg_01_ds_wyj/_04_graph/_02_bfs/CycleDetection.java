package alg_01_ds_wyj._04_graph._02_bfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 18:50
 * @Version 1.0
 */
public class CycleDetection {
    private Graph graph;
    private boolean[] visited;
    private int[] prevs;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        Arrays.fill(prevs, -1);

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        prevs[v] = v;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    prevs[w] = cur;
                } else {
                    if (w != prevs[cur]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        CycleDetection graphBFS = new CycleDetection(graph);
        System.out.println(graphBFS.hasCycle()); // true
    }
}
