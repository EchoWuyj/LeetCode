package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 20:51
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves3 {

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, root);
    }

    public int dfs(TreeNode node, TreeNode parent) {
        if (node == null) return 0;
        if (node.left == null
                && node.right == null
                && parent.left == node) {
            return node.val;
        }

        int left = dfs(node.left, node);
        int right = dfs(node.right, node);

        return left + right;
    }
}
