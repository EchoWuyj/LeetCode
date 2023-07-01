package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 14:14
 * @Version 1.0
 */
public class _03_429_n_ary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                list.add(cur.val);
                for (Node child : cur.children) {
                    if (child != null) queue.offer(child);
                }
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, res);
        return res;
    }

    public void dfs(Node root, int curLevel, List<List<Integer>> res) {
        if (root == null) return;

        if (res.size() == curLevel) {
            List<Integer> levelNodes = new ArrayList<>();
            levelNodes.add(root.val);
            res.add(levelNodes);
        } else {
            res.get(curLevel).add(root.val);
        }

        for (Node child : root.children) {
            dfs(child, curLevel + 1, res);
        }
    }
}
