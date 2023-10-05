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
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null && t2 != null) return false;
        if (t1 != null && t2 == null) return false;
        if (t1.val != t2.val) return false;

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(root);
        queue2.offer(root);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode cur1 = queue1.poll();
            TreeNode cur2 = queue2.poll();

            if (cur1 == null && cur2 == null) continue;
            if (cur1 == null || cur2 == null) return false;
            if (cur1.val != cur2.val) return false;

            queue1.offer(cur1.left);
            queue1.offer(cur1.right);

            queue2.offer(cur2.right);
            queue2.offer(cur2.left);
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
}
