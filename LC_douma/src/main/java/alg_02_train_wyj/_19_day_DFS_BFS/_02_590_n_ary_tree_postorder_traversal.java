package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 13:05
 * @Version 1.0
 */
public class _02_590_n_ary_tree_postorder_traversal {

    public List<Integer> postorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    public void dfs(Node root, List<Integer> res) {
        if (root == null) return;
        for (Node node : root.children) {
            dfs(node, res);
        }
        res.add(root.val);
    }

    public List<Integer> postorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.addFirst(cur.val);
            for (Node node : cur.children) {
                if (node != null) stack.push(node);
            }
        }
        return res;
    }
}
