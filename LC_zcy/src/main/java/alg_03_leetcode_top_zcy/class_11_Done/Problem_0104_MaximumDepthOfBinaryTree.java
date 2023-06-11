package alg_03_leetcode_top_zcy.class_11_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 14:48
 * @Version 1.0
 */
public class Problem_0104_MaximumDepthOfBinaryTree {

    /*
     * 注意最小高度比这个复杂,要额外小心判断空
     * */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 卡下root是叶节点的情况,避免调用更多递归
        // 使用 && 表示同时成立
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 空间复杂度做不到O(1),递归栈空间大小是树的高度
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
