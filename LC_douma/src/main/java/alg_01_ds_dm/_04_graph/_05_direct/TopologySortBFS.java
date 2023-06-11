package alg_01_ds_dm._04_graph._05_direct;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 16:27
 * @Version 1.0
 */
// KeyPoint 通过 bfs 来实现拓扑排序
public class TopologySortBFS {
    private GraphImpl g;
    // 存储拓扑排序的结果
    private int[] res;
    private boolean hasCycle = false;

    public TopologySortBFS(GraphImpl g) {

        // 构建入度表
        int[] indegrees = new int[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            indegrees[v] = g.indegree(v);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < g.getV(); v++) {
            // 入度为 0 的顶点放进队列
            if (indegrees[v] == 0) {
                queue.add(v);
            }
        }
        this.res = new int[g.getV()];
        // 用于遍历 res 数组的索引 index
        int index = 0;
        // KeyPoint 通过简单例子，结合代码，模拟执行过程，从而分析代码是否有问题
        while (!queue.isEmpty()) {
            int v = queue.remove();
            res[index++] = v;
            // 更新 v 的相邻顶点 w 的入度值
            // KeyPoint 在有向图中，v 相邻的顶点 w，边是有方向的，即 v -> w
            for (int w : g.adj(v)) {
                indegrees[w]--;
                if (indegrees[w] == 0) {
                    queue.add(w);
                }
            }
        }
        // 队列为空，说明已经没有入度为 0 的顶点可以遍历了 => 有环情况
        if (index != g.getV()) {
            hasCycle = true;
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        // 有环
        GraphImpl g = new GraphImpl("LC_douma/data/directedgraph-dfs-2.txt", true);
        TopologySortBFS bfs = new TopologySortBFS(g);
        System.out.println(bfs.isHasCycle()); // true
        System.out.println(Arrays.toString(bfs.getRes())); // [0, 0, 0, 0, 0]
    }

    private static void test1() {
        // 无环
        GraphImpl g = new GraphImpl("LC_douma/data/directedgraph-dfs-1.txt", true);
        TopologySortBFS bfs = new TopologySortBFS(g);
        System.out.println(bfs.isHasCycle()); // false
        System.out.println(Arrays.toString(bfs.getRes())); // [0, 1, 3, 2, 4]
    }
}
