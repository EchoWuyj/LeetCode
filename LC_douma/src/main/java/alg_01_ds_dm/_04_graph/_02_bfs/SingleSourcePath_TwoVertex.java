package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 20:16
 * @Version 1.0
 */
public class SingleSourcePath_TwoVertex {
    private Graph graph;
    private boolean[] visited;
    private int[] prevs;

    private int source;
    private int target;

    // 指定 source 和 target
    public SingleSourcePath_TwoVertex(Graph graph, int source, int target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];

        Arrays.fill(this.prevs, -1);
        // 调用 bfs 建立 prevs，为了给 path() 方法使用
        bfs(source);
    }

    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // 若 cur 就是 target 节点，则直接返回
            if (curr == target) {
                return;
            }
            for (int w : graph.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点
                    prevs[w] = curr;
                }
            }
        }
    }

    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public List<Integer> path() {
        List<Integer> res = new ArrayList<>();
        // 1. 如果源顶点到不了目标顶点，直接返回
        if (!isConnected()) {
            return res;
        }
        // 2. 根据 prevs 信息找到路径
        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);
        // 3. 翻转
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        SingleSourcePath_TwoVertex graphBFS = new SingleSourcePath_TwoVertex(graph, 0, 6);
        System.out.println(graphBFS.path()); // [0, 2, 6]
    }
}
