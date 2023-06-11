package alg_01_ds_wyj._02_tree._01_btree.train;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 20:33
 * @Version 1.0
 */
public class _101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    public static boolean isSymmetric1(TreeNode root) {
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

            // 依次进队
//            if (cur1.left != null) queue1.offer(cur1.left);
//            if (cur1.right != null) queue1.offer(cur1.right);
//
//            if (cur2.right != null) queue2.offer(cur2.right);
//            if (cur2.left != null) queue2.offer(cur2.left);

            queue1.offer(cur1.left);
            queue1.offer(cur1.right);

            queue2.offer(cur2.right);
            queue2.offer(cur2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root_1 = new TreeNode(1);
        TreeNode root_2l = new TreeNode(2);
        TreeNode root_2r = new TreeNode(2);
        TreeNode root_3l = new TreeNode(3);
        TreeNode root_3r = new TreeNode(3);

        root_1.left = root_2l;
        root_1.right = root_2r;
        root_2l.right = root_3l;
        root_2r.right = root_3r;

        System.out.println(isSymmetric1(root_1));
    }
}
