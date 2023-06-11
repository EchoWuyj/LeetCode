package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 20:16
 * @Version 1.0
 */
// 单源最短路径 => dfs 做不到，因为不能保证是最短的
//             => 只能是 bfs，bfs 是一层一层遍历的，能保证单源路径的最短
public class SingleSourceShortestPath {
    private Graph graph;
    private boolean[] visited;
    // 记录每个顶点的前一个顶点
    private int[] prevs;
    // 每一个顶点到源顶点的距离
    private int[] distance;
    private int source;

    public SingleSourceShortestPath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        this.distance = new int[graph.getV()];
        Arrays.fill(this.prevs, -1);
        // 初始化为 -1，表示距离不可达
        Arrays.fill(this.distance, -1);
        // bfs 的过程中，维护 distance[]
        bfs(source);
    }

    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;
        // 源顶点 distance 为 0
        distance[v] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int w : graph.adj(curr)) {
                // KeyPoint 如果程序运行一直没有停下来，估计是没有加 visited 判断，因而产生了环
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点
                    prevs[w] = curr;
                    // 维护顶点 w 的距离为前一个顶点的距离 +1
                    distance[w] = distance[curr] + 1;
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

    // O(n)
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
        // 将 source 也要添加到 res 中
        res.add(source);

        // 3. 翻转
        Collections.reverse(res);
        return res;
    }

    // 返回从 source 到 target 两点之间的距离
    // O(n) -> O(1)
    public int distance(int target) {
        // O(n) 解法
        // 通过 path 记录沿途的路径，将其节点个数减 1，得到的就是边的个数
        // A -> B -> C -> D  一共 4 个顶点，3 条边
        // 计算 distance 时，还是需要遍历一般 path 的
//        return path(target).size() - 1;

        // O(1) 解法
        // 在 bfs 过程中，在遍历每个节点时，维护该顶点到原顶点的距离，故只需一次遍历即可，提高性能
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
