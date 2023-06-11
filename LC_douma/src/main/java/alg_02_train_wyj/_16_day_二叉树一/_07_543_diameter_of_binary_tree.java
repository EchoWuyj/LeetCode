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
        postOrder(root);
        return ans;
    }

    public int postOrder(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = postOrder(root.left);
        int rightMaxDepth = postOrder(root.right);
        ans = Math.max(ans, rightMaxDepth + leftMaxDepth);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
