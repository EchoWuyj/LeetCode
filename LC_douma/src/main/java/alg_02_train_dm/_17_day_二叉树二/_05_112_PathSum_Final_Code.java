package alg_02_train_dm._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */
public class _05_112_PathSum_Final_Code {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0;
        }

        boolean isLeftHasPathSum = hasPathSum(root.left, targetSum - root.val);
        // 提前退出 => 剪枝操作，提高性能
        // 返回值 boolean 处理方式
        if (isLeftHasPathSum) return true;
        boolean isRightHasPathSum = hasPathSum(root.right, targetSum - root.val);

        return isLeftHasPathSum || isRightHasPathSum;
    }
}
