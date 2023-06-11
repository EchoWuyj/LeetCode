package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 20:15
 * @Version 1.0
 */

// 连通分量 + 连通分量顶点数
public class CC_Count_Vertex {
    private Graph graph;
    // 连通分量每个顶点的值
    private int[] visited;
    // 连通分量的个数
    private int ccCount;

    public CC_Count_Vertex(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                bfs(v, ccCount);
            }
        }
    }

    private void bfs(int v, int ccId) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        // 维护节点所属的连通分量
        visited[v] = ccId;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // KeyPoint 一定是当前 cur 的相邻节点，而不是 v 的相邻节点
            //      区别于:dfs 中的 v
            for (int w : graph.adj(curr)) {
                // 表示没有访问
                if (visited[w] == -1) {
                    queue.add(w);
                    // 维护节点所属的连通分量
                    visited[w] = ccId;
                }
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        // 连通分量的个数 => ArrayList 的个数
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

    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        CC_Count_Vertex vertex = new CC_Count_Vertex(graph);
        // 通过 Arrays.toString 将 List<Integer>[] 直接输出打印
        System.out.println(Arrays.toString(vertex.components())); // [[0, 1, 2, 3, 4, 5, 6]]
        System.out.println(vertex.getCcCount()); // 1
    }
}
