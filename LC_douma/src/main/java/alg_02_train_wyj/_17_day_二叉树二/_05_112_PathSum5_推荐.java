package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 23:40
 * @Version 1.0
 */
public class _05_112_PathSum5_推荐 {
    public boolean hasPathSum(TreeNode root, int target) {

        if (root == null) return false;
        target -= root.val;
        if (root.left == null && root.right == null) {
            return target == 0;
        }
        boolean left = hasPathSum(root.left, target);
        if (left) return true;
        boolean right = hasPathSum(root.right, target);
        return left || right;
    }

    private boolean hasPath = false;

    public boolean hasPathSum1(TreeNode root, int target) {
        if (root == null) return true;
        dfs(root, target);
        return hasPath;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) return;
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            hasPath = true;
            return;
        }
        dfs(root.left, target);
        dfs(root.right, target);
    }
}
