package alg_02_train_dm._22_day_回溯算法一.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 19:18
 * @Version 1.0
 */
public class GraphDFS {
    public List<Integer> dfs(Graph g) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[g.getV()];
        // 对所有顶点进行遍历，避免有非连通图
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                dfs(g, v, res, visited);
            }
        }
        return res;
    }

    private void dfs(Graph g, int v, List<Integer> res, boolean[] visited) {
        res.add(v);
        visited[v] = true;
        // 递归遍历顶点相邻没有访问的顶点
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(g, w, res, visited);
            }
        }
    }

    /*
         回溯思想:本质 => 穷举(枚举)搜索，将所有情况都穷举，逐一进行判断
                      => 通过递归实现，递的过程 => 访问某个节点(深入处理某种情况)
                                      归的过程 <=> 回溯，即回到递归的上一层，基于上一层
                                                   再去访问其他节点(深入处理其他情况)
     */
}

