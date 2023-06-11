package alg_01_新手班_wyj.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 12:35
 * @Version 1.0
 */
public class Code02_SameTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }

        if (p == null && q == null) {
            return true;
        }

        // pq都不为null
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);

    }
}