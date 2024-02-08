package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 19:59
 * @Version 1.0
 */
public class _32_124_maxPathSum {

    private int maxPathSum = Integer.MIN_VALUE;

    // 二叉树最大路径和
    // 深度优先遍历
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        // 避免出现负数
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        // 返回之前计算：左子树 + 右子树 + 中间节点值
        maxPathSum = Math.max(maxPathSum, left + right + root.val);
        // 左右子树中只能选择一条较大值 + 根节点 => 返回单条路径
        return Math.max(left, right) + root.val;
    }
}
