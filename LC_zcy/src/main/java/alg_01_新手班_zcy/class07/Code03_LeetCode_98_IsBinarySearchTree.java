package alg_01_新手班_zcy.class07;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 23:48
 * @Version 1.0
 */

// 测试 leetcode.cn/problems/validate-binary-search-tree/

public class Code03_LeetCode_98_IsBinarySearchTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 验证二叉搜索树
    // 给你一个二叉树的根节点root,判断其是否是一个有效的二叉搜索树

    // 有效二叉搜索树定义如下
    //  1)节点的左子树只包含 小于 当前节点的数
    //  2)节点的右子树只包含 大于 当前节点的数
    //  3)经典的二叉搜索树中是没有重复值的!
    // 所有左子树和右子树自身必须也是二叉搜索树

    // 定义递归返回节点信息
    // 节点信息需要定义全,需要对每个节点都要适用
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

    // 判断搜索二叉树的原则
    //  1)左,右子树都是搜索二叉树
    //  2)同时左树上所有节点都比X要小(X>leftMax)
    //  3)并且右数上所有节点都比X要大(X<rightMin)
    public static Info process(TreeNode x) {
        if (x == null) {
            // 空树不能将min和max设置为0,如果节点为负值,则是比0还小的,没法判断是搜索二叉树
            // 所以这里是单纯返回一个null即可,判断是否为null信息交给上游处理
            return null;
        }

        // 直接向左右子树要3个信息
        // 注意这里leftInfo和rightInfo可能为null
        // 后面在使用过程需腰进行判空,否则空指针异常
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 最开始以当前节点的值为max和min
        int max = x.val;
        int min = x.val;

        // 上游判空条件
        // 获取当前节点为根所在树的最大值和最小值
        // 这里只是单纯收集信息,为了将Info信息补全
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        // 先假设认为整颗树时搜索二叉树,同时将所有的违规条件都将其列全
        // 只要下面有一个不是搜索二叉树的条件满足,则不是搜索二叉树
        boolean isBST = true;

        // 左右子树不为null且左右子树不为搜索二叉树
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }

        // 比较左子树的最小值,右子树的最大值分别与x的大小
        // letMax < x
        // rightMin > x
        boolean leftMaxLessX = (leftInfo == null) ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = (rightInfo == null) ? true : (rightInfo.min > x.val);

        if (!(leftMaxLessX && rightMinMoreX)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    // 实现方式二
    public static Info process2(TreeNode x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.val;
        int min = x.val;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        // 一开始假设为false情况
        boolean isBST = false;

        // 全达标的条件都满足,则将false修改成true;
        boolean leftIsBst = leftInfo == null ? true : leftInfo.isBST;
        boolean rightIsBst = rightInfo == null ? true : rightInfo.isBST;

        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);

        if (leftIsBst && rightIsBst && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }
}
