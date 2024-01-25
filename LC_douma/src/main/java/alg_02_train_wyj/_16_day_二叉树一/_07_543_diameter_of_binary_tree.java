package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 14:42
 * @Version 1.0
 */
public class _07_543_diameter_of_binary_tree {

    private int res;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left + right);
        return Math.max(right, left) + 1;
    }
}
