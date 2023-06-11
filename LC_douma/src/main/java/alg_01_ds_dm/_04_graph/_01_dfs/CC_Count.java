package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 16:10
 * @Version 1.0
 */

// KeyPoint 求图的连通分量的个数 => 基于 DFS 算法修改而来
// CC:Connected Component
public class CC_Count {
    private Graph graph;
    // 用于防止一个节点被重复访问
    private boolean[] visited;
    // 记录连通分量个数
    private int ccCount = 0;

    public CC_Count(Graph graph) {
        this.graph = graph;
        // 后续代码调用 getV() 方法
        if (graph == null) return;
        // 数组大小和顶点个数保持一致
        this.visited = new boolean[graph.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                ccCount++;
                // 在非连通图的遍历中，每个连通分量都应该进行依次 dfs
                // 故做几次 dfs 应该就有几个连通分量
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    // 对外提供的接口
    public int getCcCount() {
        return ccCount;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-2.txt");
        CC_Count graphDFS = new CC_Count(graph);
        System.out.println(graphDFS.getCcCount()); // 2
    }
}
