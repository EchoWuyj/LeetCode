package alg_03_leetcode_top_wyj.class_11;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 13:27
 * @Version 1.0
 */
public class Problem_0104_MaximumDepthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
