package alg_01_ds_wyj._01_line._05_algo._02_recusion;

import java.util.List;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 13:03
 * @Version 1.0
 */
public class Test_02 {
    public static void visitArr(int[] arr, int start, int end) {
        if (start > end) return;
        System.out.println(arr[start]);
        visitArr(arr, start + 1, end);
    }

    public static void visitLinkedList(ListNode node) {
        if (node == null) return;
        System.out.println(node.val);
        visitLinkedList(node.next);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void visitBinaryTree(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        visitBinaryTree(root.left);
        visitBinaryTree(root.right);
    }

    public static void visitTree(Node root) {
        if (root == null) return;
        System.out.println(root.val);
        for (Node child : root.children) {
            visitTree(child);
        }
    }

    // 图遍历
    public static void visitGraph(TreeSet<Integer>[] graph, int v, boolean[] visited) {
        System.out.println(v);
        // 判断一个顶点是否已经被访问过了,若该顶点已被访问,则不需要递归遍历
        visited[v] = true;
        for (Integer w : graph[v]) {
            if (!visited[w]) {
                visitGraph(graph, w, visited);
            }
        }
    }

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 3, 4, 8};
        visitArr(arr, 0, arr.length - 1);
    }
}
