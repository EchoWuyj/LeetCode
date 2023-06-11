package _01_linkedlist;

import sun.security.action.PutAllAction;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-26 15:56
 * @Version 1.0
 */
public class Offer_77_SortList {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static class NodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    // 使用使用优先级队列
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 注意:需要将比较的方法输入构造方法中
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new NodeComparator());
        ListNode cur = head;
        while (cur != null) {
            heap.add(cur);
            cur = cur.next;
        }
        ListNode newHead = heap.poll();
        cur = newHead;
        // KeyPoint 注意事项
        //  1)heap.isEmpty(),而不是heap != null
        //  2)在while循环中,cur指针一定要随着遍历节点而不断移动!
        //  3)单链表结束一定需要在末尾加上null,表示结束,否则报错有环
        //  4)循环条件中的定义的变量,最好提前定义好
        while (!heap.isEmpty()) {
            cur.next = heap.poll();
            cur = cur.next;
        }
        // 原链表之间是存在next指针之间的关联的,如果不修改可能存在环
        cur.next = null;
        return newHead;
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(7);
        head1.next = new ListNode(9);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(8);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next.next = new ListNode(5);

        // printList(head1);
        ListNode result = sortList(head1);
        printList(result);
    }
}
