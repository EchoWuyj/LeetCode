package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 14:38
 * @Version 1.0
 */
public class Graph_DFSR {

    private Graph graph;
    // 存储 dfs 结果集
    private List<Integer> res;
    // 用于防止一个节点被重复访问
    private boolean[] visited;

    public Graph_DFSR(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        // 在构造方法中进行初始化
        this.res = new ArrayList<>();
        this.visited = new boolean[graph.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        // 将其加入到 res 结果集中
        res.add(v);
        visited[v] = true;
        // v 顶点其他相邻顶点 w
        for (int w : graph.adj(v)) {
            // KeyPoint
            //  1 递归退出条件，当所有节点都已经被访问之后，if 不成立，则不会执行后面的 dfs
            //  2 针对是 w 进行判断 visited，递归传入参数也是 w
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    // 返回 dfs 的结果集
    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        // 图结构 => 参考 PPT 上
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-1.txt");
        Graph_DFSR graph_DFS = new Graph_DFSR(graph);
        // KeyPoint 即使调用多次 getRes，图的 dfs 过程只是执行一遍，避免重复执行，提高性能
        System.out.println(graph_DFS.getRes()); // [0, 1, 3, 2, 6, 5, 4]
    }
}
