package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 18:30
 * @Version 1.0
 */

// 求指定顶点 source 到 target 两者之间的 path
public class TwoVertexPath {
    private Graph graph;
    private int source;
    private int target;

    // 用于防止一个节点被重复访问
    private boolean[] visited;
    private int[] prevs;
    // 结果集合定义在全局属性
    private List<Integer> res;

    // 求 source 到 target 两者之间的 path
    public TwoVertexPath(Graph graph, int source, int target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.res = new ArrayList<>();
        this.visited = new boolean[graph.getV()];
        prevs = new int[graph.getV()];

        // 将每个顶点的前一个顶点初始化为 -1
        for (int i = 0; i < graph.getV(); i++) {
            prevs[i] = -1;
        }
        // 深度优先遍历，这里只需要从指定的源顶点 source 遍历就可以
        // 源顶点的前一个顶点设置为源顶点本身
        dfs(source, source);

        // 计算 source 到 target 的路径，并且只是计算一次即可
        path();
    }

    // 递归遍历顶点 v，并且维护顶点 v 的前一个顶点的信息
    // KeyPoint 递归函数返回为 boolean 值，和原始 void 情况不一样，注意区别
    private boolean dfs(int v, int prev) {
        visited[v] = true;
        // 维护顶点 v 的前一个顶点
        prevs[v] = prev;
        // 找到之后直接返回 true，提前结束递归遍历
        if (v == target) return true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                // v 是 w 的前一个顶点
                // 如果已经找到了，不用再去遍历 v 的下一个相邻节点了
                // KeyPoint 这里需要使用 if 条件判断包一层，找到之后直接返回
                if (dfs(w, v)) return true;
            }
        }
        // 没有找到返回 false，则是一直找下去
        return false;
    }

    // 判断 source 和 target 是否连通
    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    // 验证顶点的有效性
    private void validateVertex(int v) {
        if (v < 0 || v >= graph.getV()) {
            throw new IllegalArgumentException("顶点不合法，超出范围");
        }
    }

    // 记录 source 到 target 的路径
    private List<Integer> path() {
        // 1. 如果源顶点到不了目标顶点，直接返回
        if (!isConnected()) {
            return res;
        }
        // 2. 根据 prevs 信息找到路径
        // KeyPoint 使用 tmp 来代替 target，主要是 path 没有传入
        //      形参 target，只能自己内部定义一个变量 tmp
        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);

        // 3. 翻转
        Collections.reverse(res);
        return res;
    }

    // 对外提供的接口，res 保存的是 source 到 target 的路径
    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-3.txt");
        TwoVertexPath graphDFS = new TwoVertexPath(graph, 0, 6);
        System.out.println(graphDFS.getRes()); // [0, 1, 3, 6]
    }
}
