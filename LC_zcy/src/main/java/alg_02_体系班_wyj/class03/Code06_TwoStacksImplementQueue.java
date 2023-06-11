package alg_02_体系班_wyj.class03;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-15 21:18
 * @Version 1.0
 */
public class Code06_TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> pollStack;

        public TwoStacksQueue() {
            pushStack = new Stack<>();
            pollStack = new Stack<>();
        }

        public void pushToPoll() {
            if (pollStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    pollStack.push(pushStack.pop());
                }
            }
        }

        public void add(int num) {
            pushStack.push(num);
            pushToPoll();
        }

        public int poll() {
            if (pushStack.isEmpty() && pollStack.isEmpty()) {
                throw new RuntimeException(" Queue is empty");
            }
            pushToPoll();
            return pollStack.pop();
        }

        public int peek() {
            if (pushStack.isEmpty() && pollStack.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }

            pushToPoll();
            return pollStack.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
