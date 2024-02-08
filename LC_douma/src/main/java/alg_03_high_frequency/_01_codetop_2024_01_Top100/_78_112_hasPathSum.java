package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:45
 * @Version 1.0
 */
public class _78_112_hasPathSum {
    private boolean hasPath = false;

    //
    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;
        dfs(root, target);
        return hasPath;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) return;
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            hasPath = true;
            return;
        }
        dfs(root.left, target);
        dfs(root.right, target);
    }
}
