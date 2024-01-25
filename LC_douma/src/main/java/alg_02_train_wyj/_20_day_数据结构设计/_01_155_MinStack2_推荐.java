package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-11-21 16:29
 * @Version 1.0
 */
public class _01_155_MinStack2_推荐 {
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

    class MinStack {
        Deque<Integer> minStack;
        Deque<Integer> dataStack;

        public MinStack() {
            minStack = new ArrayDeque<>();
            dataStack = new ArrayDeque<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int pop = dataStack.pop();
            if (minStack.peek() == pop) {
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
}
