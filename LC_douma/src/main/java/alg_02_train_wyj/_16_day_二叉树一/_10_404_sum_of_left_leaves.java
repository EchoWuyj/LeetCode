package alg_02_train_wyj._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 16:11
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (isLeafNode(cur.left)) {
                    sum += cur.left.val;
                } else {
                    queue.offer(cur.left);
                }
            }
            if (cur.right != null && !isLeafNode(cur.right)) {
                queue.offer(cur.right);
            }
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private int sum = 0;

    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) return 0;
        preOrder(root, root);
        return sum;
    }

    public void preOrder(TreeNode root, TreeNode parent) {
        if (root == null) return;
        if (root.left == null && root.right == null && parent.left == root) {
            sum += root.val;
        }

        preOrder(root.left, root);
        preOrder(root.right, root);
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        return postOrder(root, root);
    }

    public int postOrder(TreeNode root, TreeNode parent) {
        if (root == null) return 0;
        if (root.left == null && root.right == null
                && parent.left == root) {
            return root.val;
        }

        int left = postOrder(root.left, root);
        int right = postOrder(root.right, root);
        return left + right;
    }

    public void postOrder1(TreeNode root, TreeNode parent) {
        if (root == null) return;
        postOrder1(root.left, root);
        postOrder1(root.right, root);
        if (root.left == null && root.right == null
                && parent.left == root) {
            sum += root.val;
        }
    }
}
