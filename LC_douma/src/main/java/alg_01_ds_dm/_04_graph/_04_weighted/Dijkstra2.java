package alg_01_ds_dm._04_graph._04_weighted;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 19:45
 * @Version 1.0
 */
// 时间复杂度：O(V * (logV + E))
public class Dijkstra2 {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;
    // 保存每个顶点的前一个顶点，用于求解最短沿途的各个顶点
    private int[] prevs;

    private class Pair implements Comparable<Pair> {
        int v;
        int dis;

        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pair o) {
            return dis - o.dis;
        }
    }

    public Dijkstra2(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;
        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        Arrays.fill(prevs, -1);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source, 0));
        while (!pq.isEmpty()) { // O(V)
            // 1. 找到当前没有访问的最短路径节点
            int curVer = pq.poll().v; // O(logV)
            if (visited[curVer]) continue;

            // 2. 确认这个节点的最短路径就是当前大小
            visited[curVer] = true;

            // 3. 根据这个节点的最短路径大小，更新其他节点的路径长度
            for (int w : g.adj(curVer)) { // O(E)
                if (!visited[w] && (distance[curVer] + g.getWeight(curVer, w) < distance[w])) {
                    distance[w] = distance[curVer] + g.getWeight(curVer, w);
                    pq.add(new Pair(w, distance[w]));
                    // KeyPoint 处理相邻顶点时，对 prevs 进行维护，记录 w 的前一个顶点 curVer
                    prevs[w] = curVer;
                }
            }
        }
    }

    public int minDistanceTo(int v) {
        validateVertex(v);
        return distance[v];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException(String.format("顶点 %d 不合格", v));
        }
    }

    // 判断是否连通
    public boolean isConnected(int v) {
        validateVertex(v);
        // 只要设置为 true ，则是 v 必然是连通的
        return visited[v];
    }

    // KeyPoint 返回最短路径中所有的顶点
    // 注意是 Collection，不是 Collections，Collections 是工具类
    public Collection<Integer> path(int target) {
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
        WeightedAdjSet g = new WeightedAdjSet("LC_douma/data/Dijkstra.txt");
        Dijkstra2 dijkstra = new Dijkstra2(g, 0);
        System.out.println(dijkstra.minDistanceTo(3)); // 5
        System.out.println(dijkstra.path(3));  // [0, 2, 1, 3]
        System.out.println(dijkstra.minDistanceTo(4)); // 6
        System.out.println(dijkstra.path(4));  // [0, 2, 1, 4]
    }
}
