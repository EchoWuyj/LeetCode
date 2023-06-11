package alg_01_ds_wyj._04_graph._04_weighted;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 13:04
 * @Version 1.0
 */
public class Dijkstra1 {
    private WeightedAdjSet g;
    private int source;
    private int[] distance;
    private boolean[] visited;

    private class Pair implements Comparable<Pair> {
        int v;
        int dis;

        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

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

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(source, 0));
        while (!pq.isEmpty()) {
            int curVer = pq.poll().v;
            if (visited[curVer]) continue;
            visited[curVer] = true;
            for (int w : g.adj(curVer)) {
                if (!visited[w] && (distance[curVer] + g.getWeight(curVer, w) < distance[w])) {
                    distance[w] = distance[curVer] + g.getWeight(curVer, w);
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
