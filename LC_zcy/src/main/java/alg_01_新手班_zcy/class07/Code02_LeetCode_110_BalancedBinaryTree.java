package alg_01_新手班_zcy.class07;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 23:48
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/balanced-binary-tree

public class Code02_LeetCode_110_BalancedBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 平衡二叉树
    // 给定一个二叉树,判断它是否是高度平衡的二叉树
    // 本题中一棵高度平衡二叉树定义为
    // 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1

    // 解题思路
    //       x
    //  左树  右树

    // 想让x树时平衡树
    //   左树是平衡的
    //   左树是平衡的
    //   同时左树和右树的高度差的绝对值<=1

    // 在以某个节点为头时,计算结果需要返回两个信息
    //   1)正颗树是否平衡
    //   2)正颗树的高度
    // 因此需要做出这样的Info结构

    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    // 递归关键:
    // 往下传递什么?
    // 往上传递什么?

    // 递归函数,返回值为info
    public static Info process(TreeNode root) {
        // 递归边界
        // 最后递归到一个空树时的情况
        if (root == null) {
            // 因为递归到null情况,所以直接可以获得isBalanced和height
            return new Info(true, 0);
        }

        // root != null
        // 不断地往左右子树递进
        // 返回结果有左右子树的平衡信息和高度信息
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        // 整颗树的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        // 判断是否平衡
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
                // <2或者<=1的条件不能忘记
                && Math.abs(leftInfo.height - rightInfo.height) < 2;

        return new Info(isBalanced, height);
    }

    public static boolean isBalanced(TreeNode root) {
        // 主方法中不进行判空,留给递归函数进行判空
        return process(root).isBalanced;
    }
}
