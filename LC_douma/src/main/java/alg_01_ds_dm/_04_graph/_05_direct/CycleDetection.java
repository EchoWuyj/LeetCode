package alg_01_ds_dm._04_graph._05_direct;

import alg_01_ds_dm._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 15:05
 * @Version 1.0
 */

// KeyPoint
//  1 基于有向图的深度优先遍历 => 有向图环检测
//  2 处理逻辑:在 dfs 中给遍历的顶点打上标识，在检测到一个顶点的相邻顶点
//            已经被遍历过且被打上标识，则说明构成环
public class CycleDetection {
    private Graph g;
    private boolean hasCycle = false;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    // KeyPoint 用于给遍历过程中的每个顶点打上标识
    //  递的过程 => true
    //  归的过程 => false
    private boolean[] isOnPath;

    public CycleDetection(Graph g) {
        this.g = g;
        if (g == null) return;
        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    // 跳出循环，不用再去 for 循环遍历
                    break;
                }
            }
        }
    }

    private boolean dfs(int v) {
        visited[v] = true;
        // 递的过程 => 访问一个顶点 => 设置为 true
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // 因为 dfs 的返回值为 boolean，需要对 dfs 加上一层 if 判断
                // 如果 dfs 中检测出来环，直接返回 true，不需要再进行遍历了
                if (dfs(w)) return true;
            } else {
                // 否则，w 顶点已经被访问，并且有蓝色标记，则说明有环
                if (isOnPath[w]) return true;
            }
        }
        // for 循环结束，回溯的过程，将蓝色标记去掉
        // 归的过程 => 设置为 false;
        isOnPath[v] = false;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        // 有环图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-2.txt", true);
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.isHasCycle()); // true
    }

    private static void test1() {
        // 无环图
        Graph g = new GraphImpl("LC_douma/data/directedgraph-dfs-1.txt", true);
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.isHasCycle()); // false
    }
}
