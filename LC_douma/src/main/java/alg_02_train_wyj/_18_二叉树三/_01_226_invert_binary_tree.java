package alg_02_train_wyj._18_二叉树三;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 16:10
 * @Version 1.0
 */
public class _01_226_invert_binary_tree {

    private TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    private TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return root;
    }

    private TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return root;
    }
}
