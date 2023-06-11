package _03_binary_tree;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 19:57
 * @Version 1.0
 */
public class Offer_28_IsSymmetric {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return process(root, root);
    }

    public static boolean process(TreeNode r1, TreeNode r2) {
        if (r1 == null ^ r2 != null) {
            return false;
        }
        if (r1 == null && r2 == null) {
            return true;
        }

        return r1.val == r2.val
                && process(r1.left, r2.right)
                && process(r1.right, r2.left);
    }
}
