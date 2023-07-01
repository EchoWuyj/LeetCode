package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 20:15
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree4 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == 0) {
            return right + 1;
        } else if (right == 0) {
            return left + 1;
        } else {
            return Math.min(left, right) + 1;
        }
    }
}
