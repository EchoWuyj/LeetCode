package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:38
 * @Version 1.0
 */
public class _62_110_isBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return dfs(root) >= 0;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }
}
