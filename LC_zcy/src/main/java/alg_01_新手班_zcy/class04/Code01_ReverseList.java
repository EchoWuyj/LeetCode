package alg_01_新手班_zcy.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:35
 * @Version 1.0
 */
public class Code01_ReverseList {

    // KeyPoint 链表文件关键在于对边界的判断,是比较难考虑周全的

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last; // 上一个
        public DoubleNode next; // 下一个

        public DoubleNode(int data) {
            value = data;
        }
    }

    // KeyPoint 题目1 反转链表(遍历实现)
    public static Node reverseLinkedList(Node head) {

        // 返回值Node指针需要指向链表反转后的头节点(3节点),而不能再指向原链表的头节点(1节点)
        // 否则JVM虚拟机会认为没有引用指向后面的节点(不可达),认为其是垃圾空间,会将其释放掉

        // null <- 1 <- 2 <- 3
        //         ↑
        //        Node

        //---------------------------------------------------------------------------

        Node pre = null;
        Node next = null;

        while (head != null) {
            // 使用next指针记录head的下个位置,将其保存
            next = head.next;
            // 反转head的next指针,指向pre
            head.next = pre;

            // KeyPoint 指针先后移动顺序移动也是有讲究的

            // 先移动pre指针指向head(由null指向head)
            pre = head;
            // 再head指针后移
            head = next;
        }
        // 最后返回的是pre,此时head已经为null
        return pre;
    }

    // KeyPoint 题目2 反转双向链表II (遍历实现)
    // 整体思路单链表反转相似
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            // 后指针前移
            head.next = pre;
            // 前指针后移
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    // test for
    // Java引用的传递
    public static void f1(Node head) {
        head = head.next;
    }

    // test for
    // Java引用的传递
    public static void f2(Node head) {
        head.value = 2;
    }

    public static void print(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
        n2.next = n3;

        // 打印链表节点值,结果为123
        print(n1);
        System.out.println("=======================");

        //--------------------------------------------------------

        // Java 基本数据类型（byte、short、int、long、double、float、char、boolean）
        // 引用数据类型(类class、接口interface、数组array)

        // KeyPoint Java引用的传递
        // 将n1节点的引用传递到f函数中,即使在f函数中对n1节点进行修改,但是上游的n1指向的内存地址是不发生变化的
        // 本质:将n1的引用copy了一份n1',f函数其实是对n1'进行操作,上游n1指针并不发生变化,但是n1节点里面内容会发生变化

        System.out.println(n1); // basci.class04.Code01_ReverseList$Node@1b6d3586

        // 内部修改Node的head指针
        f1(n1);
        System.out.println(n1); // basci.class04.Code01_ReverseList$Node@1b6d3586

        // 内部修改Node的value
        System.out.println(n1.value); // 1

        f2(n1);
        System.out.println(n1.value); // 2

        //-------------------------------------------------------
    }
}
