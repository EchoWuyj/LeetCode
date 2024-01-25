package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 19:59
 * @Version 1.0
 */
public class _32_124_maxPathSum {

    // 二叉树最大路径和
    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        // 避免出现负数
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        maxPathSum = Math.max(maxPathSum, left + right + root.val);
        // 返回以根节点 + 左右子树其中一个最大值
        return Math.max(left, right) + root.val;
    }
}
