package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;
import alg_01_ds_wyj._04_graph._01_dfs.CC_Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 16:11
 * @Version 1.0
 */

// KeyPoint 求每个连通分量包含顶点 => 基于 DFS 算法修改而来
public class CC_ContainsVertex {
    private Graph graph;
    // KeyPoint 将 boolean 替换成 int 类型
    private int[] visited;
    private int ccCount = 0;

    public CC_ContainsVertex(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new int[graph.getV()];
        // KeyPoint 通过 Arrays 的 fill 方法将 visited 数组中数值设置为 -1
        Arrays.fill(visited, -1);
        // 遍历图中每个顶点
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (visited[v] == -1) {
                ccCount++;
                // 将连通分量传入到 dfs 中，故同一个连通分量中顶点的数值都是相同的
                dfs(v, ccCount);
            }
        }
    }

    private void dfs(int v, int ccId) {
        // 将 visited[v] 设置为 ccId 的值
        visited[v] = ccId;
        for (int w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccId);
            }
        }
    }

    // 连通分量的个数
    public int getCcCount() {
        return ccCount;
    }

    // 不同连通分量存储不同的结果集
    public List<Integer>[] components() {
        // list 数据类型的数组
        // KeyPoint 创建数组时，ArrayList 是不用加泛型的 <Integer>
        List<Integer>[] res = new ArrayList[ccCount];

        // KeyPoint  Arrays.fill 初始化 res => 存在问题
        // Arrays.fill(res, new ArrayList<>());
        // 若使用这种方式对 res 进行初始化，则是将同一个 new ArrayList<>() 放到 res 数组中所有位置，
        // res 每个元素操作时，所有的操作都是针对同一个 new ArrayList<>()，这样就会存在问题

        // 初始化 res
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }

        // KeyPoint 遍历 visited 数组，数组下标表示表示顶点，数组下标对应元素值即为该顶点属于那个连通分量
        for (int v = 0; v < graph.getV(); v++) {
            // 顶点属于的连通分量
            int cc = visited[v];
            // 连通分量从 1 开始，而数组下标索引从 0 开始，故需要减 1
            // 相同的连通分量的值在同一个 ArrayList 集合中
            res[cc - 1].add(v);
        }
        return res;
    }

    // 判断连通分量的两个顶点是否是连通的
    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        // visited 值相同，则是属于同一个连通分量，故是连通，否则是不连通
        return visited[v] == visited[w];
    }

    // 不需要返回值，若出现问题直接抛出异常
    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-2.txt");
        CC_ContainsVertex vertex = new CC_ContainsVertex(graph);
        System.out.println(vertex.getCcCount()); // 2
        System.out.println(Arrays.toString(vertex.components())); // [[0, 1, 2, 3, 4], [5, 6]]
        System.out.println(vertex.isConnected(0, 6)); // false
    }
}
