package alg_02_train_wyj._22_day_回溯算法一.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 19:50
 * @Version 1.0
 */
public class GraphDFS {
    public List<Integer> dfs(Graph graph) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(graph, v, res, visited);
            }
        }
        return res;
    }

    private void dfs(Graph graph, int v, List<Integer> res, boolean[] visited) {
        res.add(v);
        visited[v] = true;
        for (int w : graph.adg(v)) {
            if (!visited[w]) {
                dfs(graph, w, res, visited);
            }
        }
    }
}
