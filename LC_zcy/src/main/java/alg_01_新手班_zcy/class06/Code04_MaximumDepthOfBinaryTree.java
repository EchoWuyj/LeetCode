package alg_01_新手班_zcy.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 19:31
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/maximum-depth-of-binary-tree

public class Code04_MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    // 以root为头的树,最大高度是多少返回！
    // 方式一(普通递归)
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 最后加1是包括根节点
        // KeyPoint 多个递归函数,先执行前面的,再去执行后面的
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 方式二(二叉树递归套路)
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).height;
    }

    public static class Info {
        public int height;

        public Info(int height) {
            this.height = height;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(height);
    }

    public static void main(String[] args) {
        //    1
        //   2 3
        // 4
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);

        System.out.println(maxDepth(head)); //3
    }
}
