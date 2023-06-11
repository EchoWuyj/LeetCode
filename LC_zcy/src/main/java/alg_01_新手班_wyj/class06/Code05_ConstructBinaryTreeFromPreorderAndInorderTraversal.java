package alg_01_新手班_wyj.class06;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 23:23
 * @Version 1.0
 */
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 通过前序和中序遍历构造二叉树
    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        return process(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public static TreeNode process(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        if (L1 > R1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }

        int find = L2;
        while (in[find] != pre[L1]) {
            find++;
        }

        head.left = process(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
        head.right = process(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }

    // 优化
    public static TreeNode buildTree2(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }

        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }

        return process(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }

    public static TreeNode process(int[] pre, int L1, int R1, int[] in, int L2, int R2,
                                   HashMap<Integer, Integer> valueIndexMap) {
        if (L1 > R1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }

        Integer find = valueIndexMap.get(pre[L1]);
        head.left = process(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
        head.right = process(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
        return head;
    }
}
