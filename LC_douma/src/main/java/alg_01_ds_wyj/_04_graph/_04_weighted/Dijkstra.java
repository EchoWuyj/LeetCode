package alg_01_ds_wyj._04_graph._04_weighted;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 12:05
 * @Version 1.0
 */
public class Dijkstra {
    private WeightedAdjSet g;
    private int source;
    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;
        this.distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        visited = new boolean[g.getV()];
        while (true) {
            int curDis = Integer.MAX_VALUE;
            int curVer = -1;
            for (int v = 0; v < g.getV(); v++) {
                if (!visited[v] && distance[v] < curDis) {
                    curDis = distance[v];
                    curVer = v;
                }
            }
            if (curVer == -1) break;
            visited[curVer] = true;
            for (int w : g.adj(curVer)) {
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
    }
}
