package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 15:06
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree {
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int minDepth = Integer.MAX_VALUE;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    minDepth = Math.min(level, minDepth);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return minDepth;
    }

    class Node {
        TreeNode treeNode;
        int depth;

        public Node(TreeNode treeNode, int depth) {
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, 1));
        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            TreeNode treeNode = node.treeNode;
            int depth = node.depth;
            if (treeNode.right == null && treeNode.left == null) {
                minDepth = Math.min(depth, minDepth);
            }
            if (treeNode.right != null) stack.push(new Node(treeNode.right, depth + 1));
            if (treeNode.left != null) stack.push(new Node(treeNode.left, depth + 1));
        }

        return minDepth;
    }

    private int minDepth = Integer.MAX_VALUE;

    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        preOrder(root, 1);
        return minDepth;
    }

    public void preOrder(TreeNode root, int level) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            minDepth = Math.min(level, minDepth);
        }
        preOrder(root.left, level + 1);
        preOrder(root.right, level + 1);
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        if (root.left == null) {
            return rightMinDepth + 1;
        } else if (root.right == null) {
            return leftMinDepth + 1;
        } else {
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        }
    }
}
