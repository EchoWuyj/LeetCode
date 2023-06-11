package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:36
 * @Version 1.0
 */
public class _01_589_n_ary_tree_preorder_traversal {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.add(cur.val);
            List<Node> children = cur.children;
            int size = children.size();
            for (int i = size - 1; i >= 0; i--) {
                if (children.get(i) != null) {
                    stack.push(children.get(i));
                }
            }
        }
        return res;
    }

    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    public void dfs(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        for (Node node : root.children) {
            dfs(node, list);
        }
    }
}
