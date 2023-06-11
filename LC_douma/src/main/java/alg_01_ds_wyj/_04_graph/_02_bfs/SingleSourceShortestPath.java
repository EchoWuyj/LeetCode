package alg_01_ds_wyj._04_graph._02_bfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 20:27
 * @Version 1.0
 */
public class SingleSourceShortestPath {
    private Graph graph;
    private boolean[] visited;
    private int[] prevs;
    private int[] distance;
    private int source;

    public SingleSourceShortestPath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        this.distance = new int[graph.getV()];
        Arrays.fill(prevs, -1);
        Arrays.fill(distance, -1);
        bfs(source);
    }

    public void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        prevs[v] = v;
        distance[v] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    prevs[w] = cur;
                    distance[w] = distance[cur] + 1;
                }
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }

        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public int distance(int target) {
        validateVertex(target);
        return distance[target];
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("LC_douma/data/graph-bfs.txt");
        SingleSourceShortestPath graphBFS = new SingleSourceShortestPath(g, 0);
        System.out.println(graphBFS.path(5)); // [0, 1, 3, 5]
        System.out.println(graphBFS.distance(5)); // 3
    }
}
