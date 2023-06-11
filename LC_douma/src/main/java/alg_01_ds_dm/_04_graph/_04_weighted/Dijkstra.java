package alg_01_ds_dm._04_graph._04_weighted;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 19:44
 * @Version 1.0
 */

// 时间复杂度：O(V * (V + E))
public class Dijkstra {
    // 无向有权图
    private WeightedAdjSet g;
    private int source;
    // 每个顶点到顶点 0 的距离
    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;
        this.distance = new int[g.getV()];
        // 一开始顶点不可到达的距离为无穷大，故一开始设置为 Integer.MAX_VALUE
        // 后续有较小 distance，再去更新 distance 的值
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        visited = new boolean[g.getV()];
        while (true) { // O(V) 遍历每个顶点

            // 1. 找到当前没有访问的最短路径节点
            // 记录当前循环下最短的路径的值
            int curDis = Integer.MAX_VALUE;
            // 用来记录没有访问，且到 source 距离最短的路径的顶点
            int curVer = -1;

            // 遍历每个顶点，找到 distance 值最小的顶点和最小的 distance 值
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (!visited[v] && distance[v] < curDis) {
                    curDis = distance[v];
                    curVer = v;
                }
            }

            // while 循环的终止条件 => 说明所有顶点都已经遍历完了，直接 break 跳出 while 循环
            if (curVer == -1) break;

            // 2. 将该节点加入最短路径
            visited[curVer] = true;

            // 3. 根据这个节点的最短路径大小，更新其他节点的路径长度
            for (int w : g.adj(curVer)) { // O(E)
                if (!visited[w] && (distance[curVer] + g.getWeight(curVer, w) < distance[w])) {
                    distance[w] = distance[curVer] + g.getWeight(curVer, w);
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
        Dijkstra dijkstra = new Dijkstra(g, 0);
        System.out.println(dijkstra.minDistanceTo(3)); // 0 -> 3 最短路径 5
        System.out.println(dijkstra.minDistanceTo(4)); // 0 -> 4 最短路径 6
    }
}
