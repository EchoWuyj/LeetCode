package alg_02_体系班_wyj.class10;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 18:35
 * @Version 1.0
 */
public class Code01_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        return null;
    }

    // 1) 找到第一个入环节点
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 2)如果两个链表都无环
    public static Node noLoop(Node head1, Node head2) {
        // 1)判空
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = head1;
        Node cur2 = head2;
        int count = 0;

        // 2)cur1和cur2往下走
        while (cur1.next != null) {
            count++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            count--;
            cur2 = cur2.next;
        }
        // 3)判读cur1和cur2是否相同
        if (cur1 != cur2) {
            return null;
        }

        // 4)重定向
        cur1 = (count > 0) ? head1 : head2;
        cur2 = (cur1 == head1) ? head2 : head1;

        // 5)差值count
        count = Math.abs(count);
        while (count != 0) {
            count--;
            cur1 = cur1.next;
        }

        // 6)cur1和cur2不等一直走
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        // 7)返回相交节点
        return cur1;
    }

    // 3) 两个有环链表,返回第一个相交节点,如果不想交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = null;
        Node cur2 = null;

        // (2)
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int count = 0;
            while (cur1.next != loop1) {
                count++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2) {
                count--;
                cur2 = cur2.next;
            }

            cur1 = (count > 0) ? head1 : head2;
            cur2 = (cur1 == head1) ? head2 : head1;

            count = Math.abs(count);
            while (count != 0) {
                count--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
            // (3)和(1)
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value); // 6

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);// 2

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value); // 4
    }
}
