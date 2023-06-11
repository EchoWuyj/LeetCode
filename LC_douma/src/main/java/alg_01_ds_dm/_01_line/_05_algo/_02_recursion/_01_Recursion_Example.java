package alg_01_ds_dm._01_line._05_algo._02_recursion;

import java.util.List;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-03-07 12:02
 * @Version 1.0
 */
public class _01_Recursion_Example {

    // 各种各样递归代码 -> 本质:使用递归实现对某种数据结构遍历

    // 1 单个递归 => 数组/链表遍历
    // 2 两个递归相邻 => 二叉树遍历
    // 3 for循环里递归 => 多叉树/图遍历

    // KeyPoint 1 单个递归
    // 数组遍历
    public static void visitArr(int[] arr, int start, int end) {

        // 1 递归终止(是否是最小子问题)
        if (start > end) return;

        // 2 解决当前子问题 <=> 单层递归处理逻辑
        // 2.1 访问第一个元素值
        System.out.println(arr[start]);
        // 2.2 递归处理当前子问题的子问题
        visitArr(arr, start + 1, end);
    }

    // 链表遍历
    public static void visitLinkedList(ListNode node) {
        // KeyPoint 当前节点是否为空
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

    // KeyPoint 2 两个递归相邻
    // 二叉树遍历
    public static void visitBinaryTree(TreeNode node) {
        // 递归终止(是否是最小子问题)
        if (node == null) return;

        // 解决当前子问题
        // 1 访问当前根节点元素
        System.out.println(node.val);
        // 2 递归处理左,右子树
        visitBinaryTree(node.left);
        visitBinaryTree(node.right);

        /*
                 1
              2    3
            4  5  6  7

            1 遍历4,5节点之后,返回root节点即2节点,再思考是怎么递归来到2位置的,
              从而确定2节点递归层次,即visitBinaryTree(node.left),此时root是1
            2 再根据visitBinaryTree(node.right)遍历root的右子树

         */
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // KeyPoint 3 for循环里递归
    // 多叉树
    public static void visitTree(Node node) {
        if (node == null) return;
        System.out.println(node.val);
        // 多个子节点 => 多次递归调用
        for (Node child : node.children) {
            visitTree(child);
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

    // 图遍历
    public static void visitGraph(TreeSet<Integer>[] graph, int v, boolean[] visited) {
        // 打印输出
        System.out.println(v);
        // 判断一个顶点是否已经被访问过了,若该顶点已被访问,则不需要递归遍历
        visited[v] = true;
        for (Integer w : graph[v]) {
            if (!visited[w]) {
                visitGraph(graph, w, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 3, 4, 8};
        visitArr(arr, 0, arr.length - 1);
    }
}
