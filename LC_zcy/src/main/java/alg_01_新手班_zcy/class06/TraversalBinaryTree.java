package alg_01_新手班_zcy.class06;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 17:49
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

    //   	1
    //     ↙↘
    //    2    3
    //  ↙↘  ↙↘
    //  4  5 6   7

    //  遍历的序就是根所在的位置

    //  先序遍历:根左右
    //  1,2,4,5,3,6,7

    //  先序遍历:左根右
    //  4,2,5,1,6,3,7

    //  后续遍历:左右根
    //  4,5,2,6,7,3,1

    // 递归序
    // 1,2,4,4,4,2,5,5,5,2,1,3,6,6,6,3,7,7,7,3,1
    // 对应第几次出现的,则对应某序遍历
    // 非常有用,每个节点都遍历三次

    //     root
    // 左树    右树
    // 先通过左树要点信息回来
    // 再通过右树要点信息回来
    // 最后回到root节点,对左右数的信息进行整合

    // 先序打印所有节点
    public static void pre(Node head) {
        // 递归边界
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    // 中序
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        // 先遍历完左树
        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    // 后序
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }

    // 递归序
    public static void f(Node head) {
        if (head == null) {
            return;
        }
        // 1
        f(head.left);
        // 2
        f(head.right);
        // 3
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println();
        System.out.println("========");
        in(head);
        System.out.println();
        System.out.println("========");
        pos(head);
        System.out.println();
        System.out.println("========");
    }
}
