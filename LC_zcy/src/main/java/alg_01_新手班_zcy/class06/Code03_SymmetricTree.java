package alg_01_新手班_zcy.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 19:21
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/symmetric-tree

public class Code03_SymmetricTree {

    // 对称二叉树
    // 给你一个二叉树的根节点root检查它是否轴对称
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSymmetric(TreeNode root) {
        // 根节点不会破坏对称关系
        // KeyPoint 不一定使用原来的函数递归,可以自己定义递归函数,测试方法中进行调用
        // 不需要对root进行判空,直接调用process方法,判空条件在process里面
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode h1, TreeNode h2) {
        // 两个是否为null进行异或,只要不同的,if条件判断为true,整体解答为false;
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }

        return h1.val == h2.val
                // KeyPoint 注意事项
                //  因为除root节点之外,其最近的左右子树各有左右分支,所以都是需要进行帕判断的
                // 左和右是镜面
                && isMirror(h1.left, h2.right)
                // 右和左是镜面
                && isMirror(h1.right, h2.left);

        //      root
        //     x    x'
        //   z  y y' z'

    }
}
