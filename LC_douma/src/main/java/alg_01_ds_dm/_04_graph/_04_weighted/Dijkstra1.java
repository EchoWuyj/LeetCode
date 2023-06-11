package alg_01_ds_dm._04_graph._04_weighted;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 19:44
 * @Version 1.0
 */

// 时间复杂度：O(V * (logV + E))
public class Dijkstra1 {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    // KeyPoint 定义 Pair 类 => 内部类
    //      存储 2 个信息，顶点 v，该顶点到源顶点距离 dis
    private class Pair implements Comparable<Pair> {
        int v;
        int dis;

        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        // 比较时使用 dis 进行比较
        @Override
        public int compareTo(Pair o) {
            return this.dis - o.dis;
        }
    }

    public Dijkstra1(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        // 小顶堆，泛型是 Pair，因为小顶堆中获取的是顶点，比较的路径值，
        // 因此需要创建一个类 Pair，将这两个信息封装起来，再去加入小根堆中
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source, 0));
        while (!pq.isEmpty()) { // O(V)
            // 1. 找到当前没有访问的最短路径节点
            int curVer = pq.poll().v; // O(logV)

            // KeyPoint 可以加上冗余的判断，保证代码的健壮性
            if (visited[curVer]) continue;

            // 2. 将该节点加入最短路径
            visited[curVer] = true;

            // 3. 根据这个节点的最短路径大小，更新其他节点的路径长度
            for (int w : g.adj(curVer)) { // O(E)
                // KeyPoint 相邻的顶点一顶得保证是没有被访问的，避免走回头路
                if (!visited[w] && (distance[curVer] + g.getWeight(curVer, w) < distance[w])) {
                    distance[w] = distance[curVer] + g.getWeight(curVer, w);
                    // KeyPoint 更新 distance[w] 之后，将其再加入小根堆中，动态更新路径比较范围
                    //      每次 new Pair 的 w 都是没有被访问的，
                    pq.add(new Pair(w, distance[w]));
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

    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("LC_douma/data/Dijkstra.txt");
        Dijkstra1 dijkstra = new Dijkstra1(g, 0);
        System.out.println(dijkstra.minDistanceTo(3)); // 0 -> 3 最短路径 5
        System.out.println(dijkstra.minDistanceTo(4));  // 0 -> 4 最短路径 6
    }
}
