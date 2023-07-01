package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 19:35
 * @Version 1.0
 */

public class _09_111_minimum_depth_of_binary_tree4 {

    // KeyPoint 方法四 DFS 递归 - 后序遍历

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        // 叶子节点，深度设置为 1
        // => 从叶子节点开始，向上累加最短深度
        if (node.left == null && node.right == null) return 1;

        int left = dfs(node.left);
        int right = dfs(node.right);

        // 在获取 left 和 right 之后处理逻辑
        // 针对 left 和 right 不同情况，不同的处理方式
        // 后序遍历总体框架逻辑没有变化

        //    1
        //   / \
        // NULL 2

        // left == 0 => 左子树为 null，null 不算叶子节点
        // 故计算最小深度不看 left，直接使用 right
        // 即：返回右子树最小深度 + 1，作为该树的最小深度

        if (left == 0) {
            return right + 1;
        } else if (right == 0) {
            return left + 1;
        } else {
            // 注意：不能直接返回 Math.min(left, right) + 1
            // 这值是：在有左右子树时，最小深度求法，但是不所有情况都这样
            return Math.min(left, right) + 1;
        }
    }
}
