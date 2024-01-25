package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 16:18
 * @Version 1.0
 */
public class _01_155_MinStack1 {
    class MinStack1 {

        private Deque<Integer> stack;

        public MinStack1() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            int min = stack.peek();
            for (int i : stack) {
                min = Math.min(min, i);
            }
            return min;
        }
    }

    class MinStack3 {
        class Node {
            int data;
            int min;

            public Node(int data, int min) {
                this.data = data;
                this.min = min;
            }
        }

        Deque<Node> stack;

        public MinStack3() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            int data = val;
            int min = val;
            if (!stack.isEmpty() && stack.peek().min < val) {
                min = stack.peek().min;
            }
            stack.push(new Node(data, min));
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().data;
        }

        public int getMin() {
            return stack.peek().min;
        }
    }

    class MinStack4 {

        class ListNode {
            int data;
            int min;
            ListNode next;

            public ListNode(int data, int min) {
                this.data = data;
                this.min = min;
            }

            public ListNode() {

            }
        }

        ListNode dummy;

        public MinStack4() {
            dummy = new ListNode();
        }

        public void push(int val) {
            int min = val;
            ListNode head = dummy.next;
            if (head != null && head.min < val) {
                min = head.min;
            }
            ListNode node = new ListNode(val, min);
            node.next = dummy.next;
            dummy.next = node;
        }

        public void pop() {
            ListNode head = dummy.next;
            if (head != null) {
                dummy.next = head.next;
                head.next = null;
            }
        }

        public int top() {
            return dummy.next.data;
        }

        public int getMin() {
            return dummy.next.min;
        }
    }




    /*
      public void push(int val) {

        }

        public void pop() {

        }

        public int top() {

        }

        public int getMin() {

        }
     */
}
