package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-11-23 21:51
 * @Version 1.0
 */
public class _03_offer09_CQueue_2 {

    class MyQueue {

        Deque<Integer> mainStack;
        Deque<Integer> helpStack;

        public MyQueue() {
            mainStack = new ArrayDeque<>();
            helpStack = new ArrayDeque<>();
        }

        public void push(int x) {
            mainStack.push(x);
        }

        public int pop() {
            if (helpStack.isEmpty()) {
                while (!mainStack.isEmpty()) {
                    helpStack.push(mainStack.pop());
                }
            }
            if (helpStack.isEmpty()) {
                return -1;
            } else {
                return helpStack.pop();
            }
        }

        public int peek() {
            if (helpStack.isEmpty()) {
                while (!mainStack.isEmpty()) {
                    helpStack.push(mainStack.pop());
                }
                return helpStack.peek();
            } else {
                return helpStack.peek();
            }
        }

        public boolean empty() {
            return helpStack.isEmpty() && mainStack.isEmpty();
        }
    }
}
