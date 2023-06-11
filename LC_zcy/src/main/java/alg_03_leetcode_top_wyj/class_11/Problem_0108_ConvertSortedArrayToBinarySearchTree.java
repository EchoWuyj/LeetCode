package alg_03_leetcode_top_wyj.class_11;

import alg_01_新手班_wyj.class02.Code01_PreSum;

import java.net.PortUnreachableException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 13:34
 * @Version 1.0
 */
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }

        if (l == r) {
            return new TreeNode(nums[l]);
        }

        int m = (l + r) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = process(nums, l, m - 1);
        root.right = process(nums, m + 1, r);
        return root;
    }
}
