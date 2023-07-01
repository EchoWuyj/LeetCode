package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 16:26
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree4 {

    // KeyPoint 方法四 DFS 递归 - 后序遍历
    //                 => 从下往上计算，推荐使用
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    // 递归函数有返回值
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 核心:
        // 从左右子树获取信息，返回根节点，在根节点处进行信息加工
        // 递归遍历每颗子树，最终结果需要返回，以该 root 为根节点的最大深度

        // 左子树信息
        int left = dfs(root.left);
        // 右子树信息
        int right = dfs(root.right);
        // 信息加工后返回
        return 1 + Math.max(left, right);
    }
}
