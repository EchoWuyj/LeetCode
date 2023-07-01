package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 20:51
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves2 {

    private int sum;

    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) return 0;
        sum = 0;
        dfs(root, root);
        return sum;
    }

    public void dfs(TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (node.left == null
                && node.right == null
                && parent.left == node) {
            sum += node.val;
        }
        dfs(node.left, node);
        dfs(node.right, node);
    }
}
