package alg_02_train_wyj._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 12:17
 * @Version 1.0
 */
public class _03_145_binary_tree_postorder_traversal {
    public List<Integer> postorderTraversal1(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return res;
    }

    public List<Integer> postOrderR(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        postOrder(root, res);
        return res;
    }

    public void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }
}
