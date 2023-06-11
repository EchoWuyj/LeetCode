package alg_01_ds_wyj._04_graph._04_weighted;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 13:39
 * @Version 1.0
 */
public class Dijkstra2 {
    private WeightedAdjSet g;
    private int source;
    private int[] distance;
    private boolean[] visited;
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
        while (!pq.isEmpty()) {
            int curVer = pq.poll().v;
            if (visited[curVer]) continue;
            visited[curVer] = true;
            for (int w : g.adj(curVer)) {
                if (!visited[w] && (distance[curVer] + g.getWeight(curVer, w) < distance[w])) {
                    distance[w] = distance[curVer] + g.getWeight(curVer, w);
                    pq.add(new Pair(w, distance[w]));
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

    public boolean isConnected(int v) {
        validateVertex(v);
        return visited[v];
    }

    public Collection<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }


        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);
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
