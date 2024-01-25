package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:40
 * @Version 1.0
 */
public class _69_98_isValidBST {
    private TreeNode prev = null;
    private boolean isBST = true;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        dfs(root);
        return isBST;
    }

    // 中序遍历方式实现
    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev != null && prev.val >= node.val) {
            isBST = false;
            return;
        }
        prev = node;
        dfs(node.right);
    }
}
