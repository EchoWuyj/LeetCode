package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 16:57
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree4 {
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        int left = dfs(node.left);
        int right = dfs(node.right);
        return Math.max(left, right) + 1;
    }
}
