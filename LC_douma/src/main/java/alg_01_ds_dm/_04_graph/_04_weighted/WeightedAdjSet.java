package alg_01_ds_dm._04_graph._04_weighted;

import alg_01_ds_dm._04_graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 15:33
 * @Version 1.0
 */

// 无向有权图
// 空间复杂度：O(V + E)
public class WeightedAdjSet implements Graph {
    private int V; // 顶点的个数
    private int E; // 边的个数
    // KeyPoint 将 TreeSet[] 修改成 TreeMap[]
    //      不仅仅存储顶点，还需要存储权值
    //      key 顶点 value 权值
    private TreeMap<Integer, Integer>[] adj; // 邻接表

    // 建图时间复杂度：O(E*logV)
    public WeightedAdjSet(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);
            // KeyPoint 修改成 TreeMap
            this.adj = new TreeMap[V];
            // 每个顶点初始化一个 TreeMap
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }

            // 读取每条边的数据
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
                // KeyPoint adj 是 TreeMap[]，需要通过 a 确定是那个具体的
                //      TreeMap，同时修改成 containsKey 方法
                if (adj[a].containsKey(b)) { // O(logV)
                    throw new RuntimeException("出现了平行边，错误");
                }
                // KeyPoint 获取权值
                int weight = Integer.parseInt(arr[2]);

                // KeyPoint 修改成 put 方法
                adj[a].put(b, weight);
                adj[b].put(a, weight);
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
    // 时间复杂度：O(logV)
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        // KeyPoint 修改成 containsKey 方法
        return adj[v].containsKey(w);
    }

    // KeyPoint 获取指定边的权重值，传入参数 v 和参数 w
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        // -1 表示没有权值
        return -1;
    }

    // 获取指定顶点所有相邻的顶点
    // 时间复杂度：O(1)
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);
        // KeyPoint 修改成 keySet 方法
        return adj[v].keySet();
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
        // 遍历每个顶点
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            // KeyPoint 修改成 Map 数据结构
            Map<Integer, Integer> adjMap = adj[v];
            for (Map.Entry<Integer, Integer> entry : adjMap.entrySet()) {
                sb.append("(" + entry.getKey() + "," + entry.getValue() + ") ");
            }
            sb.append("\n");
            // Set<Map.Entry<String, Integer>> entrySet = myMap.entrySet();
            // Set 中每个元素都是一个 Map.Entry 对象，表示一个键值映射
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedAdjSet adjList = new WeightedAdjSet("LC_douma/data/weighted-graph.txt");
        System.out.println(adjList);

        /*
            顶点数 = 7，边数 = 8
            0: (1,5) (2,8)
            1: (0,5) (3,3) (4,9)
            2: (0,8) (3,4) (6,8)
            3: (1,3) (2,4) (5,6)
            4: (1,9)
            5: (3,6) (6,2)
            6: (2,8) (5,2)
         */
    }
}
