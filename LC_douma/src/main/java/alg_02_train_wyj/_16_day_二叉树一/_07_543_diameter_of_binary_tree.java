package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 14:42
 * @Version 1.0
 */
public class _07_543_diameter_of_binary_tree {

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        ans = Math.max(ans, left + right);
        return Math.max(left, right) + 1;
    }
}
