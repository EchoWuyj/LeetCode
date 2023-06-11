package alg_02_train_wyj._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 14:55
 * @Version 1.0
 */
public class _08_110_balanced_binary_tree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return postOrder(root) >= 0;
    }

    public int postOrder(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = postOrder(root.left);
        int rightMaxDepth = postOrder(root.right);

        if (leftMaxDepth == -1 || rightMaxDepth == -1) {
            return -1;
        }

        if (Math.abs(leftMaxDepth - rightMaxDepth) > 1) {
            return -1;
        }

        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
