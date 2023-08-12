package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 21:36
 * @Version 1.0
 */
public class _12_538_convert_bst_to_greater_tree {

    private int curSum = 0;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.right);
        curSum += node.val;
        node.val = curSum;
        dfs(node.left);
    }
}
