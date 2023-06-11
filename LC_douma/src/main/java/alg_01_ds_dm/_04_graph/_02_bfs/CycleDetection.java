package alg_01_ds_dm._04_graph._02_bfs;

import alg_01_ds_dm._04_graph.AdjSet;
import alg_01_ds_dm._04_graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 20:15
 * @Version 1.0
 */
public class CycleDetection {
    private Graph graph;
    private boolean[] visited;
    private int[] prevs;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        if (graph == null) return;
        this.visited = new boolean[graph.getV()];
        this.prevs = new int[graph.getV()];
        Arrays.fill(this.prevs, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int w : graph.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点
                    prevs[w] = curr;
                    // curr 的相邻顶点 w 之前遍历过，并且相邻顶点不是 curr 的前一个顶点，则说明是存在环的
                    // 因此在找环的过程中，也是需要维护前一个顶点的信息， prevs[w] = curr;
                    // KeyPoint 区别 dfs，dfs 在方法调用过程中，传入参数就是顶点 v 的前一个顶点 prev，
                    //      而 bfs 是通过循环 + 迭达来实现的，所以需要维护一个 prevs 数组
                } else {
                    // 到了这里，说明：当前的节点 curr 的相邻顶点 w 已经被访问了
                    // KeyPoint bug 修复：如果 curr 的相邻顶点 w 又不是当前顶点的前一个顶点的话，说明有环
                    if (w != prevs[curr]) {
                        return true;
                    }

                    /**
                     a——b
                     \  /
                     c
                     假设先访问 a 节点（visited[a] = true）
                     然后 b 和 c 都是 a 的相邻节点，依次将 b 和 c 放入到队列中
                     并且维护 prevs[b] = a 和 prevs[c] = a，且 visited[b] = true 和 visited[c] = true

                     然后从队列中把 b 节点拉出来，进行访问，b 的相邻节点分别是 a 和 c，我们分别来考虑

                     1 先考虑相邻的节点 a，这个时候：
                     curr = b, w = a，且 visited[w] = true，w = prevs[curr]
                     即相邻的节点已经被访问，并且这个相邻节点是当前节点的前一个节点，不能判断为有环

                     2 再来考虑相邻的节点 c，这个时候：
                     curr = b, w = c，且 visited[w] = true，w != prevs[curr]
                     即相邻的节点已经被访问，并且这个相邻的节点不是当前节点的前一个节点，此时说明有环

                     其实就是说，在 BFS 的过程中，如果有两条路可以到达同一个节点，那么就说明有环
                     比如节点 c，可以从 a -> c，也可以 a -> b -> c
                     */
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new AdjSet("LC_douma/data/graph-bfs.txt");
        CycleDetection graphBFS = new CycleDetection(graph);
        System.out.println(graphBFS.hasCycle()); // true
    }
}
