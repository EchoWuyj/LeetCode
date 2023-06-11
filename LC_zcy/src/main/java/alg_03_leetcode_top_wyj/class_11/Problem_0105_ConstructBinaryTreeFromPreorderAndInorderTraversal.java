package alg_03_leetcode_top_wyj.class_11;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 15:01
 * @Version 1.0
 */

// 从前序与中序遍历序列构造二叉树
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

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

    // KeyPoint 方法一
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode process(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
        if (l1 > r1) {
            return null;
        }

        TreeNode root = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return root;
        }

        int find = l2;
        while (in[find] != pre[l1]) {
            find++;
        }

        root.left = process(pre, l1 + 1, l1 + find - l2, in, l2, find - 1);
        root.right = process(pre, l1 + find - l2 + 1, r1, in, find + 1, r2);
        return root;
    }

    // KeyPoint 优化
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    public static TreeNode process1(int[] pre, int l1, int r1, int[] in, int l2, int r2,
                                    HashMap<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }

        TreeNode root = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return root;
        }
        int find = map.get(pre[l1]);
        root.left = process1(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, map);
        root.right = process1(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, map);
        return root;
    }
}
