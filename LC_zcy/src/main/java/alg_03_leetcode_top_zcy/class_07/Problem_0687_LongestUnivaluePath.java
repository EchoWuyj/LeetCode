package alg_03_leetcode_top_zcy.class_07;

/**
 * @Author Wuyj
 * @DateTime 2023-02-22 13:58
 * @Version 1.0
 */

// 最长同值路径
public class Problem_0687_LongestUnivaluePath {

    /*
        给定一个二叉树的root,返回最长的路径的长度,这个路径中的每个节点具有相同值
        这条路径可以经过也可以不经过根节点(沿途的点只能走一次),两个节点之间的路径长度由它们之间的边数表示

        思路:二叉树递归套路

     */

    public int longestUnivaluePath(TreeNode root) {
        return 0;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

