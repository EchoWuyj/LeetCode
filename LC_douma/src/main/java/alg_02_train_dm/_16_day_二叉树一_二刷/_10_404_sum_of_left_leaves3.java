package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 20:47
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves3 {


    // KeyPoint 方法三 DFS 后序遍历
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, root);
    }

    // 1.返回值 int 是以 node 为根节点的子树所有的左叶子之和
    // 2.先计算左右子节点的值，再返回根节点的值，本质就是后序遍历
    private int dfs(TreeNode node, TreeNode parent) {
        // 1 空树的左叶子和为 0，直接返回
        if (node == null) return 0;
        // 2 左叶子
        if (node.left == null &&
                node.right == null &&
                parent.left == node) {
            // 返回给递归上层进行计算
            return node.val;
        }

        int left = dfs(node.left, node);
        int right = dfs(node.right, node);

        // 处理当前根节点
        return left + right;
    }
}
