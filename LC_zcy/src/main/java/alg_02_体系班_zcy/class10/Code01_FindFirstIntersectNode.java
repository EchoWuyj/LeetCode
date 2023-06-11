package alg_02_体系班_zcy.class10;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:07
 * @Version 1.0
 */
public class Code01_FindFirstIntersectNode {

    // 单链表的相交节点系列问题(链表可能有环,也可能无环)
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 主函数
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 入环节点loop1
        Node loop1 = getLoopNode(head1);
        // 入环节点loop2
        Node loop2 = getLoopNode(head2);

        // 只有这两种情况
        // 1)两个无环链表相交问题
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 2)两个有环链表相交问题
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 一环一单链表的情况
        return null;
    }

    // 1)找到链表第一个入环节点,如果无环,返回null
    // 简单方法:使用HashSet
    public static Node getLoopNode(Node head) {
        // 判空
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        // 快慢指针分别走
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow和fast相遇之后,fast回到开头,此时,slow和fast每次各走一步,最终一定会在入环节点处,再次相遇
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 2)如果两个链表都无环,返回第一个相交节点,如果不想交,返回null
    // 简单方法:使用HashSet
    public static Node noLoop(Node head1, Node head2) {
        // 1)判空
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;

        // 2)cur1和cur2往下走
        // while循环中是判断next是否为null,而不是while(cur!=null)
        // 循环结束,cur1走到链表最后一个节点,不是在null上
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 3)两个链表最后一个节点内存地址不同,则不相交
        if (cur1 != cur2) {
            return null;
        }
        // 4)将长短链表重定向了
        // n>0,则head1链表更长,n<0,则head2链表更长
        // 目的是cur1和cur2节点的复用
        cur1 = n > 0 ? head1 : head2; // 谁长,谁的头变成cur1
        cur2 = (cur1 == head1) ? head2 : head1; // 谁短,谁的头变成cur2

        // 5)差值为n
        // n:链表1长度减去链表2长度的值
        n = Math.abs(n);
        // 长链表先走差值n步
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }

        // 6)cur1和cur2不等,一直往前走
        // cur1和cur2第一次相遇,跳出while循环,则为第一个相交节点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        // 7)返回相交节点
        return cur1;
    }

    // 一个链表有环,另外一个链表无环,则不可能相交,因为只有一个next指针,没法分叉
    // 3)两个有环链表,返回第一个相交节点,如果不想交返回null
    //   细分成一下几种情况:
    //   (1)链表1成环,链表2成环,两者相互独立,不相交
    //   (2)链表1,链表2相交,环在相交节点位置以下,即两个链表入环的节点是同一个
    //   (3)链表1,链表2相交,入环节点不是同一个
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        // 判空
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = null;
        Node cur2 = null;
        // 情况(2)入环节点相同
        // 以loop1和loop2为结尾,求交点的方法同noLoop
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            // 下界不在是null,而是变成loop1和loop2,即入环节点的前一个节点
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                // 只是让长链表先移动,而不是cur2一并跟着移动
                cur1 = cur1.next;
            }
            //
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 入环节点的下个节点
            cur1 = loop1.next;
            // cur1一直一步一步往下走,如果走着走着,在转回自己之前,遇到loop2,则说明是情况(3)
            // 否则,转了一圈之后,cur1再遇到loop1,跳出while循环,则说明是情况(2)
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
        // 1->2->3->4->5->6->7接null的叶子节点->null
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
