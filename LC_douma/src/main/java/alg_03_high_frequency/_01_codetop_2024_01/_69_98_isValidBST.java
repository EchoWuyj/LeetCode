package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:40
 * @Version 1.0
 */
public class _69_98_isValidBST {

    // 额外变量
    private TreeNode prev = null;
    private boolean isBST = true;

    // 验证二叉搜索树
    // 深度优先遍历
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        dfs(root);
        return isBST;
    }

    // 中序遍历方式实现
    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        // 在两个 dfs 调用之间进行判断 => 中序遍历
        // 必须严格保证 prev.val < node.val，否则就不是二叉搜索树
        if (prev != null && prev.val >= node.val) {
            isBST = false;
            // 直接返回
            return;
        }
        prev = node;
        dfs(node.right);
    }
}
