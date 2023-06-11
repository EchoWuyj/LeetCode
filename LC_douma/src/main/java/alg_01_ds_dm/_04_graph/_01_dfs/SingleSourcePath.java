package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 17:58
 * @Version 1.0
 */

// 单源路径
public class SingleSourcePath {
    private Graph graph;
    // 单源路径的源顶点
    private int source;

    // 用于防止一个节点被重复访问
    private boolean[] visited;

    // 遍历每个顶点的前一个顶点
    private int[] prevs;

    // KeyPoint 单源路径，需要在构造方法中指定源顶点，不需要对图中每个顶点进行遍历
    //      从一个源开始，在 dfs 中维护遍历每个顶点的前一个节点 prevs，
    //      根据 prevs 可以拿到源顶点到任何顶点的路径
    public SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;

        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];

        // 存储每个顶点的前一个顶点，初始化为 -1
        for (int i = 0; i < graph.getV(); i++) {
            prevs[i] = -1;
        }

        // 深度优先遍历，这里只需要从指定的源顶点遍历就可以，并不是图每个顶点都去遍历
        // 源顶点的前一个顶点设置为源顶点本身
        dfs(source, source);
    }

    // 递归遍历顶点 v，并且维护顶点 v 的前一个顶点的信息
    private void dfs(int v, int prev) {
        visited[v] = true;
        // 维护顶点 v 的前一个顶点
        // 数组的索引是当前顶点，数组的值是前一个顶点
        prevs[v] = prev;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                // w 是 v 的相邻顶点，则 v 是 w 的前一个顶点
                dfs(w, v);
            }
        }
    }

    // 判断源顶点和 target 是否连通的
    public boolean isConnected(int target) {
        validateVertex(target);
        // KeyPoint 该流程只是从 source 开始进行 dfs 遍历
        //      若target 在 visited 中若是 true，则是连通的，否则则是不连通的
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    // KeyPoint 核心代码:源顶点到任何一个顶点 target 的路径 => 单源路径
    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();

        // 1. 如果源顶点到不了目标顶点，直接返回
        if (!isConnected(target)) {
            return res;
        }

        // 2. 根据 prevs 信息找到路径
        while (target != source) {
            // 将沿途的路径节点加入 res 中
            res.add(target);
            // 更新 target
            target = prevs[target];
        }

        // 最后加入 source
        res.add(source);

        // 3. 翻转
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(graph, 0);
        System.out.println(graphDFS.path(6)); // [0, 1, 3, 2, 6]
    }

    private static void test1() {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(graph, 0);

        System.out.println(graphDFS.path(6));
        // 只是找到 0 到 6，其中一条路径， [0, 1, 3, 6]
    }
}
