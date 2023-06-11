package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 16:18
 * @Version 1.0
 */
public class _01_155_MinStack {
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
            int minValue = Integer.MAX_VALUE;
            for (int num : stack) {
                minValue = Math.min(minValue, num);
            }
            return minValue;
        }
    }

    class MinStack2 {
        Deque<Integer> dataStack;
        Deque<Integer> minStack;

        public MinStack2() {
            dataStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int top = dataStack.pop();
            if (top == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    class MinStack3 {
        class Node {
            int val;
            int minVal;

            public Node() {

            }

            public Node(int val, int minVal) {
                this.val = val;
                this.minVal = val;
            }
        }

        Deque<Node> stack;

        public MinStack3() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            Node cur = new Node();
            cur.val = val;

            int minVal = val;
            if (!stack.isEmpty() && stack.peek().minVal < minVal) {
                minVal = stack.peek().minVal;
            }
            cur.minVal = minVal;
            stack.push(cur);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().val;
        }

        public int getMin() {
            return stack.peek().minVal;
        }
    }

    class MinStack4 {

        class ListNode {
            int val;
            int min;
            ListNode next;

            public ListNode() {

            }

            public ListNode(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        ListNode dummyNode;

        public MinStack4() {
            dummyNode = new ListNode();
        }

        public void push(int val) {
            int min = val;
            ListNode head = dummyNode.next;
            if (head != null && head.min < val) {
                min = head.min;
            }
            ListNode node = new ListNode(val, min);
            node.next = head;
            dummyNode.next = node;
        }

        public void pop() {
            ListNode head = dummyNode.next;
            if (head != null) {
                dummyNode.next = head.next;
                head.next = null;
            }
        }

        public int top() {
            return dummyNode.next.val;
        }

        public int getMin() {
            return dummyNode.next.min;
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
