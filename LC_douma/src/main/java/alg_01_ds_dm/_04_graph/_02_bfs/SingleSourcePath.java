package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 20:16
 * @Version 1.0
 */
public class SingleSourcePath {
    private Graph graph;
    private boolean[] visited;
    private int[] prevs;
    private int source;

    public SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        Arrays.fill(this.prevs, -1);
        // 只是对 source 进行 bfs 即可，target 是在 path 方法中传入进来的
        bfs(source);
    }

    // 在 bfs 遍历的过程中，维护好每个顶点的前一个顶点即可
    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int w : graph.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点，w 的前一个顶点就是 cur
                    prevs[w] = curr;
                }
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        // 1. 如果源顶点到不了目标顶点，直接返回
        if (!isConnected(target)) {
            return res;
        }
        // 2. 根据 prevs 信息找到路径
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        // 3. 翻转
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(graph, 0);
        System.out.println(graphBFS.path(6)); // [0, 2, 6]
    }
}
