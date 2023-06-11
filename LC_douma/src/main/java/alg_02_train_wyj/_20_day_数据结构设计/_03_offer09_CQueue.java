package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 20:11
 * @Version 1.0
 */
public class _03_offer09_CQueue {

    class CQueue1 {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue1() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(value);
        }

        public int deleteHead() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                return -1;
            }
        }
    }

    class CQueue2 {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue2() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            } else {
                return stack2.pop();
            }
        }
    }
}
