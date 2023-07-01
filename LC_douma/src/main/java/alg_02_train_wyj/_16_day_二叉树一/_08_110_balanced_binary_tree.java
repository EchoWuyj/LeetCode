package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 14:55
 * @Version 1.0
 */
public class _08_110_balanced_binary_tree {

    public boolean isBalanced(TreeNode root) {
        return dfs(root) >= 0;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(right, left) + 1;
    }
}
