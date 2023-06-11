package alg_01_ds_wyj._04_graph._02_bfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 15:33
 * @Version 1.0
 */
public class Graph_BFS {
    private Graph graph;
    private boolean[] visited;
    private List<Integer> res;

    public Graph_BFS(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.res = new ArrayList<>();
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) bfs(v);
        }
    }

    public void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        Graph_BFS graphBFS = new Graph_BFS(graph);
        System.out.println(graphBFS.getRes()); // [0, 1, 2, 3, 4, 6, 5]
    }
}
