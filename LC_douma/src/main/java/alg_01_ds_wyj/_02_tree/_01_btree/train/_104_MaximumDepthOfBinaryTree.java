package alg_01_ds_wyj._02_tree._01_btree.train;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 15:51
 * @Version 1.0
 */
public class _104_MaximumDepthOfBinaryTree {
    private class Node {
        TreeNode node;
        int depth;

        public Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int maxDepth = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, 1));
        if (!stack.isEmpty()) {
            Node cur = stack.pop();
            int curDepth = cur.depth;
            TreeNode curTreeNode = cur.node;
            maxDepth = Math.max(maxDepth, curDepth);
            if (curTreeNode.right != null) stack.push(new Node(curTreeNode.right, curDepth + 1));
            if (curTreeNode.left != null) stack.push(new Node(curTreeNode.left, curDepth + 1));
        }
        return maxDepth;
    }

    public static int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        int maxDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            maxDepth++;
        }
        return maxDepth;
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        return Math.max(left, right) + 1;
    }
}
