package alg_02_体系班_wyj.class09;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:14
 * @Version 1.0
 */
public class Code04_CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyListWithRand1(Node head) {
        if (head == null) {
            return head;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        // 1)判空
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;

        // 2)原链表中每个节点后面创建克隆节点
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        Node curCppy = null;
        // 3)遍历一对节点,同时设置random指针
        while (cur != null) {
            next = cur.next.next;
            curCppy = cur.next;
            curCppy.rand = (cur.rand != null) ? cur.rand.next : null;
            cur = next;
        }

        cur = head;
        Node res = head.next;
        // 4)将老链表和新链表在next上分离
        while (cur != null) {
            next = cur.next.next;
            curCppy = cur.next;
            cur.next = next;
            curCppy.next = (next != null) ? next.next : null;
            cur = next;
        }
        return res;
    }
}
