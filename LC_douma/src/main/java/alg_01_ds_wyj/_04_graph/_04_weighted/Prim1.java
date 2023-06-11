package alg_01_ds_wyj._04_graph._04_weighted;

import alg_01_ds_dm._04_graph._01_dfs.CC_ContainsVertex;
import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-24 14:30
 * @Version 1.0
 */
public class Prim1 {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim1(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        // g 是连通图
        CC_ContainsVertex cc = new CC_ContainsVertex(g);
        if (cc.getCcCount() > 1) return;

        boolean[] visited = new boolean[g.getV()];
        visited[0] = true;

        // 小顶堆
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        for (int w : g.adj(0)) {
            pq.add(new WeightedEdge(0, w, g.getWeight(0, w)));
        }

        while (!pq.isEmpty()) {
            WeightedEdge minEdge = pq.poll();
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }
            result.add(minEdge);
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;
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
}
