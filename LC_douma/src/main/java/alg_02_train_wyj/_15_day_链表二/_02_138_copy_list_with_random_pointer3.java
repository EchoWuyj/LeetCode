package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-07-15 15:13
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer3 {

    public Node copyRandomList2(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }

        cur = head;
        while (cur != null) {
            cur.next.random = (cur.random != null) ? cur.random.next : null;
            cur = cur.next.next;
        }

        Node old = head;
        Node newNode = head.next;
        Node cloneHead = head.next;

        while (old != null) {
            old.next = (old.next != null) ? old.next.next : null;
            newNode.next = (newNode.next != null) ? newNode.next.next : null;
            old = old.next;
            newNode = newNode.next;
        }
        return cloneHead;
    }
}
