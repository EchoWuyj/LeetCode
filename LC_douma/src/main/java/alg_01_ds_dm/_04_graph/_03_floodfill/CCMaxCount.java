package alg_01_ds_dm._04_graph._03_floodfill;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-22 21:08
 * @Version 1.0
 */
public class CCMaxCount {
    private Graph graph;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    // 最大连通分量的顶点个数
    private int maxVertexNum = 0;

    public CCMaxCount(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                // 对每个连通分量单独一次 dfs， 遍历返回的 res，并在其中取最大值
                maxVertexNum = Math.max(dfs(v), maxVertexNum);
            }
        }
    }

    // 每次 dfs 遍历到的顶点进行统计累加到 res，最后将总的 res 返回
    private int dfs(int v) {
        visited[v] = true;
        // 当前一个顶点算为 1
        int res = 1;
        // 相邻顶点 dfs 后顶点数
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                // 将结果累加到 res
                res += dfs(w);
            }
        }

        // for 循环遍历相邻顶点结束之后的，没有符合 if 条件的顶点，递归结束
        // 返回自己当前顶点的 res = 1，累加到上层的 res 中
        return res;
    }

    public int getMaxVertexNum() {
        return maxVertexNum;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-7.txt");
        CCMaxCount graphDFS = new CCMaxCount(graph);
        System.out.println(graphDFS.getMaxVertexNum()); // 5
    }
}
