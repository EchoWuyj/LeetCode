package alg_01_新手班_wyj.class07;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 17:18
 * @Version 1.0
 */
public class Code02_BalancedBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
                && Math.abs(leftInfo.height - rightInfo.height) <= 1;

        return new Info(isBalanced, height);
    }

    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }
}
