package alg_01_新手班_wyj.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 13:35
 * @Version 1.0
 */
public class Code04_MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
