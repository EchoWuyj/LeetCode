package alg_02_train_wyj._15_day_链表二;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 23:44
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer1 {

    public static ListNode copyLinkedList(ListNode head) {
        if (head == null) return head;
        ListNode oldNode = head;
        ListNode newNode = new ListNode(oldNode.val);
        ListNode newHead = newNode;

        while (oldNode != null && oldNode.next != null) {
            newNode.next = new ListNode(oldNode.next.val);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return newHead;
    }

    public static ListNode copyLinkedList1(ListNode head) {
        return process(head);
    }

    public static ListNode process(ListNode head) {
        if (head == null) return null;

        ListNode cur = new ListNode(head.val);
        ListNode rest = process(head.next);
        cur.next = rest;
        return cur;
    }

    public static ListNode copyLinkedList2(ListNode head) {
        if (head == null) return null;
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode newHead = null;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            ListNode cur = new ListNode(pop.val);
            cur.next = newHead;
            newHead = cur;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList1(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList2(head)); // 1->2->3->4->null
    }
}
