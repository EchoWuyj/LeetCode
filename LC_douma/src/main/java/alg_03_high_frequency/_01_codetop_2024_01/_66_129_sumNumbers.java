package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:39
 * @Version 1.0
 */
public class _66_129_sumNumbers {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }

        int sum = preSum * 10 + root.val;
        // 叶子节点，直接返回
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            // 非叶子节点，则递归调用
            // 先一直向左递归，到递归边界，再去向右递归
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
}
