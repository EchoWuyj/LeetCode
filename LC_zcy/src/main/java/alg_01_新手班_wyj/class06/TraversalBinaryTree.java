package alg_01_新手班_wyj.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 11:57
 * @Version 1.0
 */
public class TraversalBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        pre(head.left);
        pre(head.right);

    }



}
