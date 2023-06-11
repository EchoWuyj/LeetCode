package alg_01_ds_wyj._01_line._03_stack.train;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 21:13
 * @Version 1.0
 */
public class _155_MinStack {

}

class MinStack {

    private Deque<Integer> stack;

    public MinStack() {
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
        for (Integer integer : stack) {
            min = Math.min(min, integer);
        }

        return min;
    }
}

class MinStack2 {
    private Deque<Integer> dataStack;
    private Deque<Integer> minStack;

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
        int min;

        public Node() {

        }

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private Deque<Node> stack;

    public MinStack3() {
        stack = new ArrayDeque<>();
    }

    public void push(int val) {
        Node node = new Node();
        node.val = val;
        int min = val;
        if (!stack.isEmpty() && val > stack.peek().min) {
            min = stack.peek().min;
        }
        node.min = min;
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
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

    private ListNode dummyHead;

    public MinStack4() {
        dummyHead = new ListNode();
    }

    public void push(int val) {
        int min = val;
        ListNode head = dummyHead.next;
        if (head != null && head.min < min) {
            min = head.val;
        }
        ListNode node = new ListNode(val, min);
        node.next = head;
        dummyHead.next = node;
    }

    public void pop() {
        ListNode head = dummyHead.next;
        if (head != null) {
            dummyHead.next = head.next;
            head.next = null;
        }
    }

    public int top() {
        return dummyHead.next.val;
    }

    public int getMin() {
        return dummyHead.next.min;
    }
}



