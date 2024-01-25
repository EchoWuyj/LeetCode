package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:40
 * @Version 1.0
 */
public class _67_543_diameterOfBinaryTree {
    private int res;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        // 最后返回为 res，不为 dfs(root)
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = dfs(root.left);
        int right = dfs(root.right);

        // 二叉树直径 = Math.max[左右子树相加]
        // 转化成二叉树最大深度问题
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }
}
