package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 12:49
 * @Version 1.0
 */
public class _08_124_binary_tree_maximum_path_sum {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return max;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);
        max = Math.max(max, left + right + node.val);
        return Math.max(right, left) + node.val;
    }
}
