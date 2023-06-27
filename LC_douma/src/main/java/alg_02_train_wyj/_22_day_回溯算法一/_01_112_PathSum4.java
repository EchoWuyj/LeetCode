package alg_02_train_wyj._22_day_回溯算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-24 20:17
 * @Version 1.0
 */
public class _01_112_PathSum4 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum - root.val == 0;

        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);

        return left || right;
    }
}
