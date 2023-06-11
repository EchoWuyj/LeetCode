package alg_02_体系班_wyj.class03;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-15 20:19
 * @Version 1.0
 */
public class Code05_GetMinStack {
    public static class MinStack {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else if (val <= getMin()) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }

            dataStack.push(val);
        }

        public void pop() {
            if (dataStack.isEmpty() || minStack.isEmpty()) {
                return;
            }
            // 同步弹出
            minStack.pop();
            dataStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                return -1;
            }

            return minStack.peek();
        }
    }
}
