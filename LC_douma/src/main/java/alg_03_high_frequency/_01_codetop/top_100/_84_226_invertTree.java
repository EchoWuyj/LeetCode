package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _84_226_invertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        // 当 invertTree(root.right) 被调用并返回结果后，root.left 被赋予了新的值。
        // 这意味着在接下来调用 invertTree(root.left) 时，实际上是在操作已经被修改过的左子树，
        // 而不是原始的左子树。这将导致算法无法正确反转树。

        // KeyPoint 错误代码
//        root.left = invertTree(root.right);
//        root.right = invertTree(root.left);

        return root;
    }
}
