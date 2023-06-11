package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 19:16
 * @Version 1.0
 */
// 环检测 => 优化
public class CycleDetection_Optimize {
    private Graph graph;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection_Optimize(Graph graph) {
        this.graph = graph;

        this.visited = new boolean[graph.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                // 根据 dfs 的返回值，对 hasCycle 进行赋值
                if (dfs(v, v)) {
                    hasCycle = true;
                    // 一旦找到环之后，就不需执行后续的代码了
                    break;
                }
            }
        }
    }

    // KeyPoint 优化 => 若检测到环了，则退出该递归，不用继续再进行检测
    private boolean dfs(int v, int prev) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                // 递归调用时，对相邻的顶点进行递归调用，若是检测到环，直接退出的，即后面的循环就可以不执行了
                if (dfs(w, v)) return true;
            } else {
                // 否则，w 顶点已经被访问，且 w 不是 v 的前一个节点的话，那么就存在环
                if (w != prev) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        test1(); // true
        test2(); // false
    }

    private static void test2() {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-4.txt");
        CycleDetection_Optimize graphDFS = new CycleDetection_Optimize(graph);
        System.out.println(graphDFS.hasCycle()); // false;
    }

    private static void test1() {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        CycleDetection_Optimize graphDFS = new CycleDetection_Optimize(graph);
        System.out.println(graphDFS.hasCycle()); // true
    }
}
