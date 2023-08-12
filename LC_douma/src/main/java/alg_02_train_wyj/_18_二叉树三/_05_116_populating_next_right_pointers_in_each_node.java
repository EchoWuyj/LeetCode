package alg_02_train_wyj._18_二叉树三;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 16:45
 * @Version 1.0
 */
public class _05_116_populating_next_right_pointers_in_each_node {
    public Node connect1(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i != size - 1) cur.next = queue.peek();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return null;
        Node left = root;
        Node cur = null;
        while (left.left != null) {
            cur = left;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            left = left.left;
        }
        return root;
    }

    public Node connect3(Node root) {
        if (root == null) return null;
        dfs(root);
        return root;
    }

    public void dfs(Node node) {
        if (node == null) return;
        Node left = node.left;
        Node right = node.right;
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        dfs(node.left);
        dfs(node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
}
