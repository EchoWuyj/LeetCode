package alg_01_ds_dm._04_graph._04_weighted;

import alg_01_ds_dm._04_graph._01_dfs.CC_ContainsVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 16:43
 * @Version 1.0
 */

// KeyPoint 时间复杂度：O(V*V*E)
public class Prim {
    // Prim 基于无向有权图
    private WeightedAdjSet g;
    // 将无向有权图的边抽象成 WeightedEdge
    // 最小生成树是有若干边组成，使用 List 来装边，边的集合
    private List<WeightedEdge> result;

    // 从外部传入无向有权图
    public Prim(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        // KeyPoint 求解最小生成树，需要保证 g 是连通图，故需要先进行验证
        //      CC_ContainsVertex 是面向 Graph 接口编程的，
        //      同时 WeightedAdjSet 实现了 Graph 接口，故可以将其传入
        CC_ContainsVertex cc = new CC_ContainsVertex(g);
        // 不是连通图，直接 return
        if (cc.getCcCount() > 1) return;

        // 顶点是否被访问，通过是否被访问，来区别是否被切分
        boolean[] visited = new boolean[g.getV()];
        // 选择顶点 0 作为切分的一部分
        visited[0] = true;

        // 进行 v - 1 次切分，每次切分得到最短的横切边
        // KeyPoint 切分可以理解成:将顶点纳入最小生成树中，顶点 0 已经加入到最短生成树中了
        //      剩下的顶点个数为 g.getV() - 1，故需要循环这么多次，将顶点依次加入最小生成树
        for (int i = 0; i < g.getV() - 1; i++) { // O(V)
            // 定义最小的横切边，需要找最小值，故设置 Integer.MAX_VALUE 进行比较
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);

            // 扫描所有顶点，当前顶点被访问且相邻的顶点没有被访问，才是横切边
            // 通过比较所有的横切边，从而得到最小的横切边，将其加入结果集合
            for (int v = 0; v < g.getV(); v++) { // O(V)
                // 切分里的顶点
                if (visited[v]) {
                    for (int w : g.adj(v)) { // O(E) => 访问每个顶点的相邻边，所以是 O(E) 时间复杂度
                        // KeyPoint 核心操作-1  v-w 横切边，找到最小的横切边
                        if (!visited[w] && g.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, g.getWeight(v, w));
                        }
                    }
                }
            }
            // 将最小的横切边放入结果集中
            result.add(minEdge);

            // KeyPoint 核心操作-2 扩展切分
            //  => 将最小的横切中没有被访问的顶点设置为 true
            //  => 无向有权图的边抽象成 WeightedEdge，数据结构中定义了 getV 和 getW 方法
            int v = minEdge.getV();
            int w = minEdge.getW();
            int newV = visited[v] ? w : v;
            visited[newV] = true;
        }

        // KeyPoint 优化
        //  Prim 性能瓶颈:寻找最短的横切边的逻辑，需要两次 for 循环，寻找最值，
        //    => 同时寻找横切边的过程中，横切边比较范围是不断变换，在变化的数据中找最值
        //    => 联想小根堆来解决，不断往小根堆扔数据，堆顶时刻保持最小值
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
