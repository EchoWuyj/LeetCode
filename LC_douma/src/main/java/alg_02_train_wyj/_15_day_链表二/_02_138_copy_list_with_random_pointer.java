package alg_02_train_wyj._15_day_链表二;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 23:44
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer {

    // 单链表
    public static ListNode copyLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode oldHead = head;
        ListNode newNode = new ListNode(head.val);
        ListNode newHead = newNode;
        while (oldHead != null && oldHead.next != null) {
            newNode.next = new ListNode(oldHead.next.val);
            oldHead = oldHead.next;
            newNode = newNode.next;
        }
        return newHead;
    }

    public static ListNode copyLinkedList1(ListNode head) {
        if (head == null) return null;
        ListNode curNode = new ListNode(head.val);
        curNode.next = copyLinkedList1(head.next);
        return curNode;
    }

    public static ListNode copyLinkedList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newHead = null;
        while (!stack.isEmpty()) {
            ListNode top = stack.pop();
            ListNode curNode = new ListNode(top.val);
            curNode.next = newHead;
            newHead = curNode;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
    }

    private Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node curNode = new Node(head.val);
        map.put(head, curNode);

        curNode.next = copyRandomList(head.next);
        curNode.random = map.get(head.random);

        return curNode;
    }

    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();

        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        map.put(oldNode, newNode);

        Node newHead = newNode;
        while (oldNode != null) {
            newNode.next = getClone(oldNode.next, map);
            newNode.random = getClone(oldNode.random, map);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return newHead;
    }

    public Node getClone(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        if (!map.containsKey(node)) {
            map.put(node, new Node(node.val));
        }
        return map.get(node);
    }

    // 新旧链表交替
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        // 设置正确的 random
        cur = head;
        while (cur != null) {
            cur.next.random = (cur.random != null) ? cur.random.next : null;
            cur = cur.next.next;
        }

        Node oldList = head;
        Node newList = head.next;
        Node newHead = head.next;
        while (oldList != null) {
            oldList.next = (oldList.next != null) ? oldList.next.next : null;
            newList.next = (newList.next != null) ? newList.next.next : null;

            oldList = oldList.next;
            newList = newList.next;
        }
        return newHead;
    }
}
