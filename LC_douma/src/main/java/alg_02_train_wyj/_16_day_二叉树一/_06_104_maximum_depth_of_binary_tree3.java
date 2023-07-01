package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 16:52
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree3 {

    private int maxDepth;

    public int maxDepth3(TreeNode root) {
        if (root == null) return 0;
        maxDepth = 0;
        dfs(root, 1);
        return maxDepth;
    }

    public void dfs(TreeNode node, int depth) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
