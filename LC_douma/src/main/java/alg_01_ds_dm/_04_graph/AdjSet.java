package alg_01_ds_dm._04_graph;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 21:19
 * @Version 1.0
 */
//
public class AdjSet implements Graph {

    private int V; // 顶点的个数
    private int E; // 边的个数

    // KeyPoint 使用 TreeSet 替换 HashSet 提高查询速度
    // 1 TreeSet(红黑树) 能保证不浪费空间，在数据量特别大时，增删查的综合性能好，且每个邻接表中数据是有序的
    // 2 HashSet 是基于哈希实现的，在数据特别大时，容易造成哈希冲突，从而影响性能.但数据量小时，可以使用 HashSet
    // 邻接表 => 空间复杂度：O(V + E) => 以后都是使用 TreeSet 作为邻接表底层实现
    private TreeSet<Integer>[] adj;

    // 建图时间复杂度：O(E*logV)
    public AdjSet(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);
            // 创建 TreeSet 数组时，不需要加上 <E>
            this.adj = new TreeSet[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
                // KeyPoint 通过自定义比较器来实现降序排列，注意 Lambda 表达式的写法
//                adj[i] = new TreeSet<>((o1, o2) -> o2 - o1);
            }
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
                if (adj[a].contains(b)) { // O(logV)
                    throw new RuntimeException("出现了平行边，错误！");
                }
                // KeyPoint 底层是 TreeSet，将顶点加入邻接表中，其链表一定是升序排列
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
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d \n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append("顶点: " + v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("LC_douma/data/graph.txt");
        System.out.println(adjSet);

        /*
            顶点数 = 7，边数 = 9
            顶点: 0: 1 3
            顶点: 1: 0 2 6
            顶点: 2: 1 3 5
            顶点: 3: 0 2 4
            顶点: 4: 3 5
            顶点: 5: 2 4 6
            顶点: 6: 1 5
         */
    }
}
