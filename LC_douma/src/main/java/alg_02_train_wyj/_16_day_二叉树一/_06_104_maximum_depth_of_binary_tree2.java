package alg_02_train_wyj._16_day_二叉树一;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 16:32
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree2 {
    class Node {
        TreeNode node;
        int depth;

        Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        int maxDepth = 0;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            TreeNode node = cur.node;
            int curDepth = cur.depth;

            if (node.left == null && node.right == null) {
                maxDepth = Math.max(maxDepth, curDepth);
            }
            if (node.right != null) {
                stack.push(new Node(node.right, curDepth + 1));
            }
            if (node.left != null) {
                stack.push(new Node(node.left, curDepth + 1));
            }
        }
        return maxDepth;
    }
}
