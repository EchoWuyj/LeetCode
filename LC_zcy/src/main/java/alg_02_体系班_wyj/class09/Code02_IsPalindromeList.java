package alg_02_体系班_wyj.class09;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-24 15:05
 * @Version 1.0
 */
public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node fast = head;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        Stack<Node> stack = new Stack<>();
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

    public static boolean isPalindrome3(Node head) {
        // 判空
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow中点
        // 右侧逆序
        fast = slow.next;
        slow.next = null;
        Node help = null;
        while (fast != null) {
            help = fast.next;
            fast.next = slow;
            slow = fast;
            fast = help;
        }

        // 两侧往中间对数
        fast = head;
        help = slow;
        boolean res = true;
        while (fast != null && slow != null) {
            if (fast.value != slow.value) {
                res = false;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        // 逆序再调整
        slow = help.next;
        help.next = null;
        while (slow != null) {
            fast = slow.next;
            slow.next = help;
            help = slow;
            slow = fast;
        }

        return res;
    }
}
