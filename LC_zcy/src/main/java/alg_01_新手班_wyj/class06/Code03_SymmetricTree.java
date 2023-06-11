package alg_01_新手班_wyj.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 13:29
 * @Version 1.0
 */
public class Code03_SymmetricTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSymmetric(TreeNode root) {
        //
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode h1, TreeNode h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }

        if (h1 == null && h2 == null) {
            return true;
        }

        return h1.val == h2.val
                && isMirror(h1.left, h2.right)
                && isMirror(h1.right, h2.left);
    }
}
