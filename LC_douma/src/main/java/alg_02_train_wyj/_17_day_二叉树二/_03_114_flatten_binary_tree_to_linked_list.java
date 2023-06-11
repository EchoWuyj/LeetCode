package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 23:35
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list {
    public void flatten1(TreeNode root) {
        if (root == null) return;
        ArrayList<TreeNode> res = new ArrayList<>();
        preOrder(root, res);
        for (int i = 0; i < res.size() - 1; i++) {
            TreeNode prev = res.get(i);
            TreeNode cur = res.get(i + 1);
            prev.left = null;
            prev.right = cur;
        }
    }

    public void preOrder(TreeNode root, ArrayList<TreeNode> res) {
        if (root == null) return;
        res.add(root);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    public void flatten2(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.poll();
            if (prev != null) {
                prev.right = cur;
                prev.left = null;
            }
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
            prev = cur;
        }
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode left = cur.left;
                TreeNode prev = left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = cur.right;
                cur.left = null;
                cur.right = left;
            }
            cur = cur.right;
        }
    }
}
