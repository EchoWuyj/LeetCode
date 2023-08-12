package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 23:35
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list {
    public void flatten1(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    public void preOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public static void flatten(TreeNode root) {
        if (root == null) return;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int size = list.size();
        root.left = null;
        root.right = null;

        // 串联指针
        TreeNode cur = root;
        root.val = list.get(0);
        root.left = null;
        root.right = null;
        for (int i = 1; i < size; i++) {
            TreeNode next = new TreeNode(list.get(i));
            cur.right = next;
            cur = next;
        }

        // System.out.println(print(root));
    }

    public static void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten(root);
    }

    public void flatten2(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.poll();
            if (pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            TreeNode left = cur.left, right = cur.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            pre = cur;
        }
    }

    public void flatten3(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode left = cur.left;
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.left = null;
                cur.right = left;
            }
            cur = cur.right;
        }
    }
}
