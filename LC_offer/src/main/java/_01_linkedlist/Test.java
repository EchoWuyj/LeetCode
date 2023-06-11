package _01_linkedlist;

import java.time.temporal.ValueRange;

/**
 * @Author Wuyj
 * @DateTime 2022-09-26 18:18
 * @Version 1.0
 */
public class Test {

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        printList(head);
    }
}
