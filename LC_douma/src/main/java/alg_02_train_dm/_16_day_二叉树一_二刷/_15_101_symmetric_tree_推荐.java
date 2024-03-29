package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 23:42
 * @Version 1.0
 */

// KeyPoint 详细注释 _101_SymmetricTree
public class _15_101_symmetric_tree_推荐 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root, root);
    }

    // DFS
    private boolean isSymmetric1(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        // 一棵树镜像对称 => 两颗树镜像对称
        return isSymmetric1(p.left, q.right) && isSymmetric1(p.right, q.left);
    }

    // BFS
    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.val != node2.val) {
                return false;
            }
            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;
            if (left1 == null ^ right2 == null) {
                return false;
            }
            if (right1 == null ^ left2 == null) {
                return false;
            }
            if (left1 != null) {
                queue1.offer(left1);
            }
            if (right2 != null) {
                queue2.offer(right2);
            }

            if (left2 != null) {
                queue2.offer(left2);
            }
            if (right1 != null) {
                queue1.offer(right1);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
