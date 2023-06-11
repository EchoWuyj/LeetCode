package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 13:45
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree {
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            level++;
        }
        return level;
    }

    class Node {
        TreeNode treeNode;
        int depth;

        public Node(TreeNode treeNode, int depth) {
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, 1));
        int maxDepth = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode treeNode = node.treeNode;
            int depth = node.depth;
            if (treeNode.right == null && treeNode.left == null) {
                maxDepth = Math.max(maxDepth, depth);
            }
            if (treeNode.left != null) stack.push(new Node(treeNode.left, depth + 1));
            if (treeNode.right != null) stack.push(new Node(treeNode.right, depth + 1));
        }
        return maxDepth;
    }

    private int maxDepth;

    public int maxDepth3(TreeNode root) {
        if (root == null) return 0;
        preOrder(root, 1);
        return maxDepth;
    }

    public void preOrder(TreeNode root, int depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        preOrder(root.left, depth + 1);
        preOrder(root.right, depth + 1);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
}
