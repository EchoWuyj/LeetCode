package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 14:14
 * @Version 1.0
 */
public class _03_429_n_ary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                curLevel.add(cur.val);
                for (Node node : cur.children) {
                    if (node != null) queue.offer(node);
                }
            }
            res.add(curLevel);
        }
        return res;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, res);
        return res;
    }

    public void dfs(Node node, int curLevel, List<List<Integer>> res) {
        if (node == null) return;
        if (res.size() == curLevel) {
            List<Integer> curNode = new ArrayList<>();
            curNode.add(node.val);
            res.add(curNode);
        } else {
            res.get(curLevel).add(node.val);
        }

        for (Node child : node.children) {
            dfs(child, curLevel + 1, res);
        }
    }
}
