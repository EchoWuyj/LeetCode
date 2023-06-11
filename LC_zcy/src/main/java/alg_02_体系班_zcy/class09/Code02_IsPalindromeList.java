package alg_02_体系班_zcy.class09;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 18:17
 * @Version 1.0
 */
public class Code02_IsPalindromeList {

    // 给定一个单链表的头节点head,请判断该链表是否为回文结构
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space
    // 使用栈(使用容器)
    // 1)遍历链表,将所有节点都压栈,出栈则为原链表的逆序
    // 2)再遍历链表,和栈中弹出一个元素进行比较是否相等
    //   即也就是正序和链表的逆序一一比较,从而实现回文判断
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node fast = head;
        // 依次压栈
        while (fast != null) {
            stack.push(fast);
            fast = fast.next;
        }
        // 出栈比较
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need n/2 extra space
    // 节省空间,并不需要将元素全局放入栈中,只需要放入一半就可以
    // 使用快慢指针定位中点的位置,奇数唯一中点的后一个节点,偶数下中点,将对应的节点加入
    // 从头开始遍历,和栈中元素进行比对
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 只少有2节点的情况,快慢指针的分配情况
        // 思考快慢指针分配的位置
        Node slow = head.next;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Stack<Node> stack = new Stack<Node>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need O(1) extra space
    // 1)奇数长度返回中点,偶数长度返回上中点,将其next指针置null
    // 2)将中点位置指针反向,首尾两侧依次向里移动,判断值是否相同
    // 3)再返回是否为回文之前,再将链表调整回去,再去返回是否是回文
    public static boolean isPalindrome3(Node head) {
        // 判空,if判断出来,至少2点节点
        if (head == null || head.next == null) {
            return true;
        }

        // 快慢指针
        Node slow = head;
        Node fast = head;
        // 至少3个节点执行while循环
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow是中点

        // 右半部分逆序
        fast = slow.next;
        slow.next = null;
        Node help = null;
        while (fast != null) {
            help = fast.next;
            // 因为fast都是指向slow,所以slow是要跟着fast后移的
            // 而不能slow一直停在原来的位置
            fast.next = slow;
            // slow后移过程
            slow = fast;
            fast = help;
        }

        // 使用temp保存最后一个节点的位置
        help = slow;
        fast = head;
        boolean res = true;

        // 两侧往中间对数
        while (slow != null && fast != null) {
            if (slow.value != fast.value) {
                res = false;
                // break是跳出目前循环,若多层for循环,则是里面for循环
                // break和continue对if都是没影响的
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        // 最后一个节点的next指针已经修改了,指向的是前面的节点
        slow = help.next;
        // help为最后一个节点位置,之前next指针向前指,现在需要修改回来,往后指向
        help.next = null;

        // 将右部分调整成正确的,之后才能return
        while (slow != null) {
            // fast保存当前节点的前一个节点,fast辅助作用
            fast = slow.next;
            slow.next = help;
            help = slow;
            slow = fast;
        }
        return res;
    }

    // 扩展:相同的思路求解
    //  1)当链表长度为奇数时,L1L2L3L4 R1R2R3R4 应调整成 L1R1L2R2L3R3L4R4 的形式
    //  2)当链表长度为偶数时,L1L2L3L4 R1R2R3R4R5 应调整成 L1R1L2R2L3R3L4R4R5 的形式

    // for test
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
