package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:38
 * @Version 1.0
 */
public class _62_110_isBalanced {

    // 平衡二叉树
    // 深度优先遍历
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        // 只要 dfs 结果 >= 0，则是平衡二叉树，否则不是
        return dfs(root) >= 0;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        // 计算左右子树深度
        int left = dfs(root.left);
        int right = dfs(root.right);

        // 先判断
        if (left == -1 || right == -1) {
            return -1;
        }

        // 再去计算
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // 以当前节点为根树的深度
        return Math.max(left, right) + 1;
    }
}
