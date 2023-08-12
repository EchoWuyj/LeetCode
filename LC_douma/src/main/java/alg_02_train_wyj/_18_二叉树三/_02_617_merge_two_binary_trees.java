package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 16:40
 * @Version 1.0
 */
public class _02_617_merge_two_binary_trees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        return dfs(root1, root2);
    }

    private TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null && root2 != null) return root2;
        if (root1 != null && root2 == null) return root1;
        TreeNode mergeNode = new TreeNode(root1.val + root2.val);
        TreeNode left = dfs(root1.left, root2.left);
        TreeNode right = dfs(root1.right, root2.right);
        mergeNode.left = left;
        mergeNode.right = right;
        return mergeNode;
    }
}
