package alg_01_新手班_wyj.class06;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-18 18:21
 * @Version 1.0
 */
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal_Web {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }

        HashMap<Integer, Integer> valueIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }

        return process(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }

    public static TreeNode process(int[] pre, int l1, int r1, int[] in, int l2, int r2, HashMap<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return head;
        }

        int find = map.get(pre[l1]);
        head.left = process(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, map);
        head.right = process(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, map);

        return head;
    }

    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};

        System.out.println(buildTree(pre, in));
    }
}
