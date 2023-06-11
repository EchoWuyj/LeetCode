package alg_01_ds_wyj._04_graph._02_bfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.Connection;
import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 16:58
 * @Version 1.0
 */
public class SingleSourcePath_TwoVertex {
    private Graph graph;
    private boolean[] visited;
    private int[] prevs;

    private int source;
    private int target;

    public SingleSourcePath_TwoVertex(Graph graph, int source, int target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        Arrays.fill(prevs, -1);
        bfs(source);
    }

    public void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == target) {
                return;
            }
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    prevs[w] = cur;
                }
            }
        }
    }

    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public List<Integer> path() {
        List<Integer> res = new ArrayList<>();
        if (!isConnected()) {
            return res;
        }

        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        SingleSourcePath_TwoVertex graphBFS = new SingleSourcePath_TwoVertex(graph, 0, 6);
        System.out.println(graphBFS.path()); // [0, 2, 6]
    }
}
