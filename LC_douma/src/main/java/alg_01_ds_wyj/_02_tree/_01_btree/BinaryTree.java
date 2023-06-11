package alg_01_ds_wyj._02_tree._01_btree;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 23:17
 * @Version 1.0
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> root_23 = new TreeNode<>(23);
        TreeNode<Integer> node_34 = new TreeNode<>(34);
        TreeNode<Integer> node_21 = new TreeNode<>(21);
        TreeNode<Integer> node_99 = new TreeNode<>(99);
        TreeNode<Integer> node_77 = new TreeNode<>(77);
        TreeNode<Integer> node_90 = new TreeNode<>(90);
        TreeNode<Integer> node_45 = new TreeNode<>(45);
        TreeNode<Integer> node_60 = new TreeNode<>(60);

        root_23.left = node_34;
        root_23.right = node_21;
        node_34.left = node_99;
        node_21.left = node_45;
        node_21.right = node_60;
        node_99.left = node_77;
        node_99.right = node_90;


        System.out.println(preOrder(root_23));  // [23, 34, 99, 77, 90, 21, 45, 60]
        System.out.println("===============");
        System.out.println(inOrder(root_23)); // [77, 99, 90, 34, 23, 45, 21, 60]
        System.out.println("===============");
        System.out.println(postOrder(root_23)); // [77, 90, 99, 34, 45, 60, 21, 23]
        System.out.println("===============");
        System.out.println(levelOrder(root_23)); // [23, 34, 21, 99, 45, 60, 77, 90]
        System.out.println("===============");
        System.out.println(levelOrder_2(root_23)); // [[23], [34, 21], [99, 45, 60], [77, 90]]
    }

    // KeyPoint 1 前序
    public static List<Integer> preOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<Integer> cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res;
    }

    // 前序(递归)
    public static List<Integer> preOrderR(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        process(root, res);
        return res;
    }

    public static void process(TreeNode<Integer> root, ArrayList<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        process(root.left, res);
        process(root.right, res);
    }

    // KeyPoint 2 中序
    public static List<Integer> inOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
        TreeNode<Integer> cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode<Integer> node = stack.pop();
            res.add(node.val);
            cur = node.right;
        }
        return res;
    }

    // 中序(递归)
    public static List<Integer> inOrderR(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private static void inOrder(TreeNode<Integer> node, List<Integer> res) {
        if (node == null) return;
        inOrder(node.left, res);
        res.add(node.val);
        inOrder(node.right, res);
    }

    // KeyPoint 3 后续遍历
    public static List<Integer> postOrder(TreeNode<Integer> root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode<Integer> cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return res;
    }

    // 后序遍历(递归)
    public static List<Integer> postOrderR(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private static void postOrder(TreeNode<Integer> node, List<Integer> res) {
        if (node == null) return;
        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.val);
    }

    // KeyPoint 4 层次遍历
    public static List<Integer> levelOrder(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> cur = queue.poll();
            res.add(cur.val);
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }

        return res;
    }

    public static List<List<Integer>> levelOrder_2(TreeNode<Integer> root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(temp);
        }
        return res;
    }
}
