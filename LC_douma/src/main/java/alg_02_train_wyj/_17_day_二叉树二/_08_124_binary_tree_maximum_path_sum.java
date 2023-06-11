package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 12:49
 * @Version 1.0
 */
public class _08_124_binary_tree_maximum_path_sum {
    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        postOrder(root);
        return maxSum;
    }

    public static int postOrder(TreeNode root) {
        if (root == null) return 0;
        int leftGain = Math.max(postOrder(root.left), 0);
        int rightGain = Math.max(postOrder(root.right), 0);
        maxSum = Math.max(maxSum, leftGain + rightGain + root.val);
        return Math.max(leftGain, rightGain) + root.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));
    }
}
