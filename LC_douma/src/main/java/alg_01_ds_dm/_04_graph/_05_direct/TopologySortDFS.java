package alg_01_ds_dm._04_graph._05_direct;

import alg_01_ds_dm._04_graph.Graph;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 16:27
 * @Version 1.0
 */
// KeyPoint 在 dfs 实现环检测的基础上，来实现拓扑排序
//          => dfs 回溯的过程就是逆向的拓扑排序过程
public class TopologySortDFS {
    private Graph g;
    private boolean hasCycle = false;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private boolean[] isOnPath;
    // 拓扑排序的顶点序列
    private int[] res;
    // 遍历 res 的索引
    private int index;

    public TopologySortDFS(Graph g) {
        this.g = g;
        if (g == null) return;
        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        this.res = new int[g.getV()];
        // 逆序遍历数组，故索引从最大值开始，index--
        this.index = this.res.length - 1;
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v) {
        visited[v] = true;
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else { // 否则，w 顶点已经被访问
                if (isOnPath[w]) return true;
            }
        }
        // 在回溯的过程中，将顶点加入结果集，同时将顶点的蓝色标记去掉
        isOnPath[v] = false;
        res[index--] = v;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }

    public static void main(String[] args) {
        test1();
        System.out.println("==============");
        test2();
    }

    private static void test2() {
        // 有环
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-2.txt", true);
        TopologySortDFS graphDFS = new TopologySortDFS(g);
        System.out.println(graphDFS.isHasCycle()); // true
        System.out.println(Arrays.toString(graphDFS.getRes())); // [0, 0, 0, 2, 4]
    }

    private static void test1() {
        // 无环
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-1.txt", true);
        TopologySortDFS graphDFS = new TopologySortDFS(g);
        System.out.println(graphDFS.isHasCycle()); // false
        System.out.println(Arrays.toString(graphDFS.getRes())); // [0, 1, 3, 2, 4]
    }
}
