package alg_01_ds_wyj._04_graph._02_bfs;

import alg_01_ds_wyj._04_graph.AdjSet;
import alg_01_ds_wyj._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 15:43
 * @Version 1.0
 */
public class CC_Count_Vertex {
    private Graph graph;
    private int[] visited;
    private int ccCount;

    public CC_Count_Vertex(Graph graph) {
        this.graph = graph;
        this.visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                bfs(v, ccCount);
            }
        }
    }

    public void bfs(int v, int ccId) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = ccId;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : graph.adj(cur)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccId;
                }
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);
        }
        return res;
    }

    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return visited[v] == visited[w];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        CC_Count_Vertex vertex = new CC_Count_Vertex(graph);
        System.out.println(Arrays.toString(vertex.components()));
        System.out.println(vertex.getCcCount()); // 1
    }
}
