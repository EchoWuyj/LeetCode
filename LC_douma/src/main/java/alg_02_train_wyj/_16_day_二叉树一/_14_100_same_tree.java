package alg_02_train_wyj._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 19:50
 * @Version 1.0
 */
public class _14_100_same_tree {
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

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
            if (left1 == null ^ left2 == null) return false;
            if (right1 == null ^ right2 == null) return false;
            queue1.offer(left1);
            queue1.offer(right1);
            queue2.offer(left2);
            queue2.offer(right2);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
