package alg_02_体系班_zcy.class10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:08
 * @Version 1.0
 */
public class Code02_RecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    //   	1
    //     ↙↘
    //    2    3
    //  ↙↘  ↙↘
    //  4  5 6   7

    //  遍历中的序就是根所在的位置

    //  先序遍历:根左右
    //  1,2,4,5,3,6,7

    //  先序遍历:左根右
    //  4,2,5,1,6,3,7

    //  后续遍历:左右根
    //  4,5,2,6,7,3,1

    // 递归序
    // 1,2,4,4,4,2,5,5,5,2,1,3,6,6,6,3,7,7,7,3,1
    // 对应第几次出现打印,则对应先中后序中的一种
    // 递归序非常有用,每个节点都遍历三次

    //     root
    // 左树    右树
    // 1)先通过左树要点信息回来
    // 2)再通过右树要点信息回来
    // 3)最后回到root节点,对左右数的信息进行整合

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

    // 先序打印所有节点
    public static void pre(Node head) {
        // 递归边界
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    // res得定义全局变量形式,不能定义在preorderTraversal方法中
    static List<Integer> res = new ArrayList<>();

    // 存在集合中输入打印
    public static List<Integer> preorderTraversal(Node root) {
        if (root == null) {
            return res;
        }
        res.add(root.value);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return res;
    }

    // 中序打印所有节点
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    // 后序打印所有节点
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
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
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");

        List<Integer> res = preorderTraversal(head);
        System.out.println(res);
    }
}
