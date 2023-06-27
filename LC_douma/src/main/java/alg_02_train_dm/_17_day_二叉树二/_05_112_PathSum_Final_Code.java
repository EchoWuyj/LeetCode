package alg_02_train_dm._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */
public class _05_112_PathSum_Final_Code {
    // 剪枝
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        // int curTargetSum = targetSum - root.val;
        // 若不全局计算 curTargetSum，则需要保证使用到 curTargetSum 位置，
        // 都是 targetSum - root.val，见 hasPathSum1 代码

        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0;
        }

        boolean isLeftHasPathSum = hasPathSum(root.left, targetSum - root.val);
        // 提前退出 => 剪枝操作，提高性能
        // 注意：返回值 boolean 处理方式
        if (isLeftHasPathSum) return true;
        boolean isRightHasPathSum = hasPathSum(root.right, targetSum - root.val);

        return isLeftHasPathSum || isRightHasPathSum;
    }

    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) return false;

        int curTargetSum = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return curTargetSum == 0;
        }

        boolean left = hasPathSum1(root.left, curTargetSum);
        if (left) return true;
        boolean right = hasPathSum1(root.right, curTargetSum);

        return left || right;
    }
}
