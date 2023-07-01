package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 20:47
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves2 {

    // KeyPoint 方法二 DFS 前序遍历
    private int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        // root 没有父节点，将 root 自己作为父节点
        dfs(root, root);
        return sum;
    }

    // 递归过程需要额外信息:当前节点和父亲节点，即通过形参传入
    // => 递归函数，形参传入信息，增加递归函数体操作空间
    private void dfs(TreeNode node, TreeNode parent) {
        if (node == null) return;

        // 处理当前节点 => 保证该节点是叶子节点，同时是 parent 的左子节点
        if (node.left == null
                && node.right == null
                // 左叶子
                && parent.left == node) {
            sum += node.val;
        }

        dfs(node.left, node);
        dfs(node.right, node);
    }

    // 后续遍历
    private void dfs1(TreeNode node, TreeNode parent) {
        if (node == null) return;
        dfs1(node.left, node);
        dfs1(node.right, node);

        // 处理当前节点
        if (node.left == null
                && node.right == null
                && parent.left == node) {
            // 使用全局变量 sum，记录遍历过程中进行计算操作的值
            // 遍历完所有节点值也计算好了
            sum += node.val;
        }
    }
}
