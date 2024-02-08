package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:39
 * @Version 1.0
 */
public class _66_129_sumNumbers {

    // 求根节点到叶节点数字之和
    // 深度优先遍历
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }

        // 通过公式，将最顶层根节点到叶子节点，沿途所有经过的节点值，通过拼接的方式进行保存
        int sum = preSum * 10 + root.val;

        // 使用 if else 语句，只有这两种情况
        // 叶子节点，直接返回
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            // 非叶子节点，则递归调用
            // 先一直向左递归，到递归边界，再去向右递归
            // 左右子树
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
}
