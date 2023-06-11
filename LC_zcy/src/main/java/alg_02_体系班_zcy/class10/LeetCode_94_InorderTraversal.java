package alg_02_体系班_zcy.class10;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-29 0:25
 * @Version 1.0
 */

// https://leetcode.cn/problems/binary-tree-inorder-traversal/

public class LeetCode_94_InorderTraversal {
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

    // 递归版本
    public List<Integer> inorderTraversal1(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        process(root, list);
        return list;
    }

    public static void process(TreeNode root, LinkedList<Integer> list) {
        if (root == null) {
            return;
        }
        process(root.left, list);
        list.add(root.val);
        process(root.right, list);
    }

    // 非递归版本
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }
}
