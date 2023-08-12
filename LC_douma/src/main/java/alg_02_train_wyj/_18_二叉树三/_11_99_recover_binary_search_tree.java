package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 23:23
 * @Version 1.0
 */
public class _11_99_recover_binary_search_tree {

    private TreeNode prev = null;
    private TreeNode y = null;
    private TreeNode x = null;

    private void recoverTree(TreeNode root) {
        dfs(root);
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev != null && node.val < prev.val) {
            y = node;
            if (x == null) {
                x = prev;
            } else {
                return;
            }
        }
        prev = node;
        dfs(node.right);
    }
}
