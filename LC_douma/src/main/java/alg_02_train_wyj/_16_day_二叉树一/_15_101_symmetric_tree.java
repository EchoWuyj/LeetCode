package alg_02_train_wyj._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 20:51
 * @Version 1.0
 */
public class _15_101_symmetric_tree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric1(root, root);
    }

    private boolean isSymmetric1(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 == null && node2 == null) continue;
            if (node1.val != node2.val) return false;

            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;

            if (left1 == null ^ right2 == null) return false;
            if (right1 == null ^ left2 == null) return false;

            queue1.offer(left1);
            queue1.offer(right1);

            queue2.offer(right2);
            queue2.offer(left2);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(root);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;
            queue1.offer(node1.left);
            queue1.offer(node1.right);
            queue2.offer(node2.right);
            queue2.offer(node2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return preOrder(root.left, root.right);
    }

    public boolean preOrder(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return preOrder(p.left, q.right) && preOrder(p.right, q.left);
    }
}
