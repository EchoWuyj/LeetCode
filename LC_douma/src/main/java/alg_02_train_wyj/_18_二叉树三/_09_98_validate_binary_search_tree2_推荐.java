package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-11-21 18:04
 * @Version 1.0
 */
public class _09_98_validate_binary_search_tree2_推荐 {

    private TreeNode prev = null;
    private boolean isValid = true;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        dfs(root);
        return isValid;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (prev != null && prev.val >= root.val) {
            isValid = false;
            return;
        }
        prev = root;
        dfs(root.right);
    }
}
