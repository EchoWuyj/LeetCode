package alg_01_ds_dm._04_graph._05_direct;

import alg_01_ds_dm._04_graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 21:42
 * @Version 1.0
 */

// 空间复杂度：O(V + E)
// 支持'无向无权图'和'有向无权图'
public class GraphImpl implements Graph {
    private int V; // 顶点的个数
    private int E; // 边的个数
    private TreeSet<Integer>[] adj; // 邻接表

    // 表示有向或无向图
    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;

    // 建图时间复杂度：O(E*logV)
    public GraphImpl(String fileName, boolean isDirected) {
        // 初始化赋值
        this.isDirected = isDirected;
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);

            this.adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }
            // 初始化
            this.indegrees = new int[V];
            this.outdegrees = new int[V];
            while ((line = reader.readLine()) != null) { // O(E)
                arr = line.split(" ");
                int a = Integer.parseInt(arr[0]);
                validateVertex(a);
                int b = Integer.parseInt(arr[1]);
                validateVertex(b);
                // 检测自环边
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误");
                }
                // 检测平行边
                if (adj[a].contains(b)) { // O(logV)
                    throw new RuntimeException("出现了平行边，错误");
                }
                // KeyPoint data 数据集中的顶点 a，b 前后位置就代表了边的方向 a -> b
                adj[a].add(b); // a -> b

                // KeyPoint 有向图，统计顶点的出度和入度
                if (isDirected) {
                    // a 增加出度
                    outdegrees[a]++;
                    // b 增加入度
                    indegrees[b]++;
                }
                // 无向图
                if (!isDirected)
                    adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("顶点 %d 不合格", v));
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
    }

    // 判断两个指定的顶点之间是否有边
    // 时间复杂度：O(logV)
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    // 获取指定顶点所有相邻的顶点
    // 时间复杂度：O(1)
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // 获取指定顶点的度数
    @Override
    public int degree(int v) {
        // 有向图
        if (isDirected) {
            throw new RuntimeException("只有无向图才可以计算顶点的度数");
        }
        validateVertex(v);
        return adj(v).size();
    }

    // KeyPoint 入度
    public int indegree(int v) {
        // 无向图
        if (!isDirected) {
            throw new RuntimeException("只有有向图才可以计算顶点的入度");
        }
        validateVertex(v);
        return indegrees[v];
    }

    // KeyPoint 出度
    public int outdegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("只有有向图才可以计算顶点的出度");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d，isDirected = %b \n", V, E, isDirected));
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        test1();
        System.out.println("=============");
        System.out.println();
        test2();
    }

    private static void test2() {
        // 有向图
        // KeyPoint graph.txt 顶点顺序是有讲究的，前后位置就代表了有向图的方向
        GraphImpl graph = new GraphImpl("LC_douma/data/graph.txt", true);
        System.out.println(graph);
        /*
            顶点数 = 7，边数 = 9，isDirected = true
            0: 1 3
            1: 2 6
            2: 3 5
            3: 4
            4: 5
            5: 6
            6:
         */
    }

    private static void test1() {
        // 无向图
        GraphImpl graph = new GraphImpl("LC_douma/data/graph.txt", false);
        System.out.println(graph);

        /*
            顶点数 = 7，边数 = 9，isDirected = false
            0: 1 3
            1: 0 2 6
            2: 1 3 5
            3: 0 2 4
            4: 3 5
            5: 2 4 6
            6: 1 5
         */
    }
}
