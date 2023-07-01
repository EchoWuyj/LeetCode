package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:36
 * @Version 1.0
 */
public class _01_589_n_ary_tree_preorder_traversal {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.add(cur.val);
            List<Node> children = cur.children;
            for (int i = children.size()-1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return res;
    }

    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    public void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        for (Node child : node.children) {
            dfs(child, res);
        }
    }
}
