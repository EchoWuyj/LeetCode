package alg_01_新手班_zcy.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 19:11
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/same-tree

public class Code02_SameTree {
    // 相同的树
    // 给你两棵二叉树的根节点p和q,编写一个函数来检验这两棵树是否相同。
    // 如果两个树在结构上相同,并且节点具有相同的值,则认为它们是相同的。

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        // 一个为null,一个不为null,这样异或才为true
        if (p == null ^ q == null) {
            return false;
        }
        // 两个都是为null
        if (p == null && q == null) {
            return true;
        }

        // p,q都不为空,使用递归判断
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);

        // 以后分析递归格式
        // p.val == q.val -> root
        // && isSameTree(p.left, q.left) -> (1)
        // && isSameTree(p.right, q.right); <- (1)

        // (1)
        // ....

    }
}
