package alg_01_新手班_wyj.class07;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 19:15
 * @Version 1.0
 */
public class Code03_IsBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int max = root.val;
        int min = root.val;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        boolean isBST = true;

        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }

        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }

        boolean leftMaxLessX = (leftInfo == null) ? true : leftInfo.max < root.val;
        boolean rightMinMoreX = (rightInfo == null) ? true : rightInfo.min > root.val;

        if (!(leftMaxLessX && rightMinMoreX)) {
            isBST = false;
        }

        return new Info(isBST, max, min);
    }

    public static Info process2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int max = root.val;
        int min = root.val;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = false;

        boolean leftIsBST = (leftInfo == null) ? true : leftInfo.isBST;
        boolean rightIsBST = (rightInfo == null) ? true : rightInfo.isBST;

        boolean leftMaxLessX = (leftInfo == null) ? true : leftInfo.max < root.val;
        boolean rightMinMoreX = (rightInfo == null) ? true : rightInfo.min > root.val;

        if (leftIsBST && rightIsBST && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }

    public static boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }
}

