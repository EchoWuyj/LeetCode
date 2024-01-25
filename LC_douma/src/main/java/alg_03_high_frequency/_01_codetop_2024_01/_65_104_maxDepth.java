package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:39
 * @Version 1.0
 */
public class _65_104_maxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = dfs(root.left);
        int right = dfs(root.right);

        return 1 + Math.max(left, right);
    }
}
