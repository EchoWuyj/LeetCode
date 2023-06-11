package alg_02_体系班_wyj.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-15 13:11
 * @Version 1.0
 */
public class Code02_DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        if (head == null) {
            return null;
        }

        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static void printNode(Node head) {
        if (head == null) {
            return;
        }

        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node6 = new Node(3);
        Node node5 = new Node(2);
        Node node7 = new Node(4);
        Node node4 = new Node(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node6.next = node5;
        node5.next = node7;
        node7.next = node4;

        printNode(node1);
        System.out.println("==================");
        Node result = removeValue(node1, 2);
        printNode(result);
    }
}
