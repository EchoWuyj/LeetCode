package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-08-10 16:28
 * @Version 1.0
 */
public class _04_236_lowest_common_ancestor_of_a_binary_tree2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == q || root == p) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
