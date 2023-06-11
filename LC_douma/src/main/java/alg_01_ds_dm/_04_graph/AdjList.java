package alg_01_ds_dm._04_graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 20:45
 * @Version 1.0
 */
public class AdjList implements Graph {

    private int V; // 顶点的个数
    private int E; // 边的个数

    // 邻接表 => 链表类型数组 LinkedList<Integer>[]，数组中单个元素是链表
    //       => 本质:数组 + 链表 => 空间复杂度：O(V + E)
    private LinkedList<Integer>[] adj;

    // 建图时间复杂度：O(E*V)
    // 建图只是一次性操作，初始化 AdjList 对象只会建一次图
    public AdjList(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);
            // 链表数组大小和 V 保持一致，保证每个顶点一条链表
            this.adj = new LinkedList[V];

            // 有多少顶点就有多少链表
            for (int i = 0; i < V; i++) {
                // 链表初始化
                adj[i] = new LinkedList<>();
            }

            // 循环遍历每条边
            while ((line = reader.readLine()) != null) { // O(E)
                arr = line.split(" ");
                int a = Integer.parseInt(arr[0]);
                validateVertex(a);
                int b = Integer.parseInt(arr[1]);
                validateVertex(b);
                // 检测自环边
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误！");
                }
                // 检测平行边
                // adj[a] 对应 a 顶点的那条链表
                if (adj[a].contains(b)) { //  O(V) => 链表最长，也只是和所有顶点有关
                    throw new RuntimeException("出现了平行边，错误！");
                }

                // add 默认是从 last 位置添加元素的，但 LinkedList 是双向链表，
                // 有 first 和 last 指针，表头或者表尾添加，时间复杂度都是 O(1)
                adj[a].add(b);
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

    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
    }

    // 判断两个指定的顶点之间是否有边
    // 时间复杂度：O(V)
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w); // O(V)
    }

    // 获取指定顶点所有相邻的顶点
    // 时间复杂度：O(1)
    @Override
    public List<Integer> adj(int v) {
        validateVertex(v);
        // 直接返回 v 所在的链表，链表里面存储就是其相邻节点
        // KeyPoint 返回所有相邻的顶点，而不是其中一个相邻顶点
        return adj[v];
    }

    // 获取指定顶点的度数
    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d \n", V, E));
        // 遍历 v 条链表
        for (int v = 0; v < V; v++) { // 遍历顶点使用变量 v
            // 标记当前顶点，输出打印
            sb.append("顶点 " + v + ": ");
            // 遍历每条链表
            for (int w : adj[v]) { // 遍历顶点相邻的顶点，使用变量 w
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjList adjList = new AdjList("LC_douma/data/graph.txt");
        System.out.println(adjList);
        /*
            顶点数 = 7，边数 = 9
            顶点 0: 1 3
            顶点 1: 0 2 6
            顶点 2: 1 3 5
            顶点 3: 0 2 4
            顶点 4: 3 5
            顶点 5: 2 4 6
            顶点 6: 1 5
         */
    }
}
