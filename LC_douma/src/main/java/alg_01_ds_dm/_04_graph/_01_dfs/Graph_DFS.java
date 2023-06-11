package alg_01_ds_dm._04_graph._01_dfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 23:27
 * @Version 1.0
 */
public class Graph_DFS {
    private Graph graph;
    private List<Integer> res;

    // 用于防止一个节点被重复访问
    // KeyPoint 这不使用 HashSet 进行判重
    // 1 HashSet 虽然查找 contains 方法的时间复杂度是 O(1)，但其底层数组很多元素为空(避免哈希冲突)
    //   有点浪费空间
    // 2 这里使用数组，同样判重的时间复杂度是 O(1)，但是数组不存在浪费空间
    private boolean[] visited;

    public Graph_DFS(Graph graph) {
        // 构造方法中进行赋值操作
        this.graph = graph;
        if (graph == null) return;
        this.res = new ArrayList<>();
        this.visited = new boolean[graph.getV()];

        // 遍历图中每个顶点(避免非连通图中有节点没有遍历到)，才能将图上所有顶点都遍历到
        for (int v = 0; v < graph.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                // dfs核心代码
                dfs(v);
            }
        }
    }

    // KeyPoint 迭代方法
    // 时间复杂度：O(V)
    // 空间复杂度：O(V)
    private void dfs(int v) { // 一开始是需要指定一个顶点的
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        while (!stack.isEmpty()) {
            // 弹出节点
            int curr = stack.pop();
            // 将弹出的节点加入结果集
            res.add(curr);
            // KeyPoint 不同的地方
            //  TreeSet 中链表中节点是升序排列，故 dfs 中先将节点值小的顶点进栈，
            //  然后再将大的顶点入栈，因此 dfs 过程中先处理大的顶点，再去处理小的顶点
            for (int w : graph.adj(curr)) {
                // 如果已经访问过了，就不压入栈，就不会再次访问了
                if (!visited[w]) {
                    stack.push(w);
                    // 压栈之后，将其设置为 true，表示即将访问了，不止真正的被访问
                    // 只是已经在栈中了，下次访问到时，就不用再将其入栈了
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {
        // treeSet 默认是升序排列
        Graph graph = new AdjSet("LC_douma/data/graph-dfs-1.txt");
        Graph_DFS graph_DFS = new Graph_DFS(graph);
        System.out.println(graph_DFS.getRes()); // [0, 2, 6, 5, 3, 1, 4]
    }
}
