package alg_01_新手班_wyj.class07;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 22:56
 * @Version 1.0
 */
public class Code04_LeetCode_112_PathSum {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isSum = false;

    public static boolean hasPathSum(TreeNode root, int sum) {
        // 最初root为null的情况需要判空
        if (root == null) {
            return false;
        }

        isSum = false;
        process1(root, 0, sum);
        return isSum;
    }

    public static void process1(TreeNode root, int preSum, int sum) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == sum) {
                isSum = true;
                return;
            }
        }

        // 一般节点
        preSum += root.val;

        if (root.left != null) {
            process1(root.left, preSum, sum);
        }

        if (root.right != null) {
            process1(root.right, preSum, sum);
        }
    }

    public static boolean process2(TreeNode root, int rest) {
        if (root.left == null && root.right == null) {
            return root.val == rest;
        }

        boolean left = (root.left == null) ? false : process2(root.left, rest - root.val);
        boolean right = (root.right == null) ? false : process2(root.right, rest - root.val);
        return left | right;
    }
}
