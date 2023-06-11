package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 20:15
 * @Version 1.0
 */

public class Graph_BFS {
    // 申明属性
    private Graph graph;
    private boolean[] visited;
    // 存储遍历过程的结果集
    private List<Integer> res;

    public Graph_BFS(Graph graph) {
        // 属性初始化
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.res = new ArrayList<>();

        // 对图中每个顶点进行遍历
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) bfs(v);
        }
    }

    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        // 最好使用 offer
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res.add(curr);
            for (int w : graph.adj(curr)) {
                // 相邻的顶点只要没有访问，则依次将其入队，并同时设置成已经访问了
                if (!visited[w]) {
                    queue.add(w);
                    // KeyPoint 只要其进队列，则标记其已经访问过了
                    visited[w] = true;
                }
            }
        }
        // KeyPoint 总结
        //  1 和树 BFS 原理上差不多
        //  2 不同点
        //     2.1 图没有根节点
        //     2.2 图的边是没有方向
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
