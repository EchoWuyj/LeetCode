package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 19:34
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree3 {

    // KeyPoint 方法三 DFS 递归 - 前序遍历
    private int res;

    public int minDepth3(TreeNode root) {
        // bug 修复：需要加上特判，要不然会返回最大值
        if (root == null) return 0;
        res = Integer.MAX_VALUE;
        dfs(root, 1);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        // 遍历到叶子节点进行判断
        if (node.left == null && node.right == null) {
            res = Math.min(res, depth);
        }

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
