package alg_01_ds_wyj._04_graph._04_weighted;

import alg_01_ds_dm._04_graph._01_dfs.CC_ContainsVertex;

import java.awt.print.Printable;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-24 13:17
 * @Version 1.0
 */
public class Prim {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        CC_ContainsVertex cc = new CC_ContainsVertex(g);
        if (cc.getCcCount() > 1) return;

        boolean[] visited = new boolean[g.getV()];
        visited[0] = true;
        for (int i = 0; i < g.getV() - 1; i++) {
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < g.getV(); v++) {
                if (visited[v]) {
                    for (int w : g.adj(v)) {
                        if (!visited[w] && g.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, g.getWeight(v, w));
                        }
                    }
                }
            }
            result.add(minEdge);
            int v = minEdge.getV();
            int w = minEdge.getW();
            int newV = visited[v] ? w : v;
            visited[newV] = true;
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }

    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("LC_douma/data/prim.txt");
        Prim prim = new Prim(adjSet);
        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }

        /*
           WeightedEdge {v = 0, w = 1, weigh = 2}
           WeightedEdge {v = 1, w = 2, weigh = 1}
           WeightedEdge {v = 0, w = 5, weigh = 2}
           WeightedEdge {v = 1, w = 4, weigh = 3}
           WeightedEdge {v = 4, w = 3, weigh = 1}
           WeightedEdge {v = 3, w = 6, weigh = 5}
         */
    }
}
