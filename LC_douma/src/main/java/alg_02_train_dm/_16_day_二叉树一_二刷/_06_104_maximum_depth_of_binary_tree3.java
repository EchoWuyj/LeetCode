package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 16:26
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree3 {

    // 定义全局变量，保证能被每个方法访问
    private int maxDepth = 0;

    // KeyPoint 方法三 DFS 递归 - 前序遍历
    //                 => 从上往下计算
    public int maxDepth3(TreeNode root) {
        dfs(root, 1);
        return maxDepth;
    }

    // 每次调用 dfs 都是访问一个节点
    // 递归没有返回值，通过全局变量 res 获取
    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        // 处理当前的节点
        if (node.left == null && node.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
