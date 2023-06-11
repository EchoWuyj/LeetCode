package alg_01_ds_dm._04_graph._04_weighted;

import alg_01_ds_dm._04_graph._01_dfs.CC_ContainsVertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 18:22
 * @Version 1.0
 */

// 时间复杂度：O(E*logE)
public class Prim1 {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim1(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        // g 是连通图
        CC_ContainsVertex cc = new CC_ContainsVertex(g);
        if (cc.getCcCount() > 1) return;

        // Prim
        boolean[] visited = new boolean[g.getV()];
        // 选择顶点 0 作为切分的一部分
        visited[0] = true;

        // 小顶堆
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        for (int w : g.adj(0)) { // O(E)
            // 将顶点 0 相邻的边放入小顶堆中
            pq.add(new WeightedEdge(0, w, g.getWeight(0, w))); // O(logE)
        }
        while (!pq.isEmpty()) { // O(E)
            // 1. 拿到最小边
            WeightedEdge minEdge = pq.poll(); // O(logE)
            // 判断最小边是否是横切边
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                // v 和 w 若都是 visited，则 minEdge 不是横切边
                continue;
            }

            // 2. 将 minEdge 加入结果集中 => 加入到最小生成树中
            result.add(minEdge);

            // 3. 扩展切分
            // 找到 minEdge 另外一端没有被访问的顶点
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            // 将没有访问的顶点设置为 true
            visited[newV] = true;

            // newV 加入最小生成树中，同时将将新的横切边放入到优先队列
            for (int w : g.adj(newV)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newV, w, g.getWeight(newV, w)));
                }
            }
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }

    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("LC_douma/data/prim.txt");
        Prim1 prim = new Prim1(adjSet);

        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }
    }

        /*
             WeightedEdge {v = 0, w = 1, weight= 2}
             WeightedEdge {v = 1, w = 2, weight= 1}
             WeightedEdge {v = 0, w = 5, weight= 2}
             WeightedEdge {v = 1, w = 4, weight= 3}
             WeightedEdge {v = 4, w = 3, weight= 1}
             WeightedEdge {v = 3, w = 6, weight= 5}
         */
}
