package alg_02_train_wyj._16_day_二叉树一;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 19:46
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree2 {
    private class Node {
        TreeNode node;
        int depth;

        Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int res = Integer.MAX_VALUE;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            int depth = cur.depth;
            TreeNode node = cur.node;
            if (node.left == null && node.right == null) {
                res = Math.min(res, depth);
            }
            if (node.right != null) stack.push(new Node(node.right, depth + 1));
            if (node.left != null) stack.push(new Node(node.left, depth + 1));
        }
        return res;
    }
}
