package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 19:57
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree3 {

    private int res;

    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MAX_VALUE;
        dfs(root, 1);
        return res;
    }

    public void dfs(TreeNode root, int level) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res = Math.min(level, res);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}
