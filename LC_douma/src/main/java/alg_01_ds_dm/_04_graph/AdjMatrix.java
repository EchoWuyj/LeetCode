package alg_01_ds_dm._04_graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 19:56
 * @Version 1.0
 */
public class AdjMatrix implements Graph {
    private int V; // 顶点的个数
    private int E; // 边的个数

    // 邻接矩阵 => 空间复杂度：O(V^2)，浪费空间
    //            因为有些顶点之间是没有边的，但是在领接矩阵中却存储了 0
    private int[][] adj;

    // 建图
    // 时间复杂度：O(E) => 图存在几条边，while 循环执行几次
    public AdjMatrix(String fileName) {
        try {
            // 缓冲字符输入流 BufferedReader
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            // 读取一行文本
            String line = reader.readLine();
            String[] arr = line.split(" ");
            // 将 String 类型转 Integer 类型

            // 顶点信息
            this.V = Integer.parseInt(arr[0]);
            // 边信息
            this.E = Integer.parseInt(arr[1]);
            // 领接矩阵
            this.adj = new int[V][V];

            // 一行一行读取
            while ((line = reader.readLine()) != null) {
                arr = line.split(" ");
                int a = Integer.parseInt(arr[0]);
                // 对顶点验证
                validateVertex(a);
                int b = Integer.parseInt(arr[1]);
                validateVertex(b);
                // 检测自环边
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误！");
                }
                // 检测平行边
                if (adj[a][b] == 1) { // ab 组成的边已经存在了，现在又重复出现了，此时为平行边
                    throw new RuntimeException("出现了平行边，错误！");
                }
                // 将 a，b 顶点构成的边设置为 1
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 对顶点验证
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("顶点 %d 不合格", v));
        }
    }

    // 获取顶点
    @Override
    public int getV() {
        return V;
    }

    // 获取边
    @Override
    public int getE() {
        return E;
    }

    // 判断两个指定的顶点之间是否有边(两个顶点是否相邻)
    // 时间复杂度：O(1) => 随机访问二维数组
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        // 邻接矩阵存储的是否为1
        return adj[v][w] == 1;
    }

    // 获取指定顶点所有相邻的顶点
    // 时间复杂度：O(V)
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);

        List<Integer> res = new ArrayList<>();
        // KeyPoint 确定行 v，依次迭代列，从而实现遍历邻接矩阵的一行
        for (int i = 0; i < V; i++) {
            // 将行的位置固定下来了，剩下的只能是列在变化
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    // 获取指定顶点的度数 => 顶点有几个邻居
    @Override
    public int degree(int v) {
        // 返回 v 的相邻顶点的个数，即为该顶点的度
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d \n", V, E));
        for (int i = 0; i < V; i++) { // 控制行
            for (int j = 0; j < V; j++) { // 控制列
                sb.append(adj[i][j] + " ");
            }
            // 在一行结束之后，通过换行进行分隔，追加一个 \n
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("LC_douma/data/graph.txt");
        System.out.println(adjMatrix);
        /*
          顶点数 = 7，边数 = 9
            0 1 0 1 0 0 0
            1 0 1 0 0 0 1
            0 1 0 1 0 1 0
            1 0 1 0 1 0 0
            0 0 0 1 0 1 0
            0 0 1 0 1 0 1
            0 1 0 0 0 1 0
         */
    }
}
