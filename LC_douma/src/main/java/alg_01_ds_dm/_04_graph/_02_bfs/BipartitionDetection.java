package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 19:17
 * @Version 1.0
 */
public class BipartitionDetection {
    private Graph graph;
    private boolean[] visited;
    // -1 表示没有染颜色，0 红色 1 蓝色
    private int[] colors;
    private boolean isBipartition = true;

    public BipartitionDetection(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.colors = new int[graph.getV()];
        Arrays.fill(this.colors, -1);

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    // 不是二分图，直接 break
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    // 在 bfs 遍历中对所有顶点进行染色，染色过程需要判断相邻的顶点颜色是否相同，不相同，则不是二分图
    private boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 起始点是用红色表示
        colors[v] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // 当前节点 cur 的相邻节点
            for (int w : graph.adj(curr)) {
                // 如果 w 没有遍历过，则需要染色
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 给顶点 w 染色，和 curr 的颜色不一样
                    colors[w] = 1 - colors[curr];
                } else if (colors[w] == colors[curr]) {
                    // 如果 w 被访问过，并且它的颜色和相邻点一样
                    // 那么可以判定不是二分图
                    return false;
                }
            }
        }
        // while 循环整个过程都没有返回 false，最终才返回 true
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("LC_douma/data/graph-bfs.txt");
        BipartitionDetection graphBFS = new BipartitionDetection(g);
        System.out.println(graphBFS.isBipartition()); // true
    }
}
