package alg_01_ds_wyj._02_tree._01_btree.train;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 18:09
 * @Version 1.0
 */
public class _100_SameTree {
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode cur1 = queue1.poll();
            TreeNode cur2 = queue2.poll();
            if (cur1.val != cur2.val) return false;

            TreeNode l1 = cur1.left, r1 = cur1.right;
            TreeNode l2 = cur2.left, r2 = cur2.right;

            if (l1 == null ^ l2 == null) return false;
            if (r1 == null ^ r2 == null) return false;

            if (l1 != null) queue1.offer(l1);
            if (l2 != null) queue2.offer(l2);
            if (r1 != null) queue1.offer(r1);
            if (r2 != null) queue2.offer(r2);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }
}
