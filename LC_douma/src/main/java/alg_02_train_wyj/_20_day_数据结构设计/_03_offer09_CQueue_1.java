package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 20:11
 * @Version 1.0
 */
public class _03_offer09_CQueue_1 {

    class CQueue1 {

        Deque<Integer> mainStack;
        Deque<Integer> helpStack;

        public CQueue1() {
            mainStack = new ArrayDeque<>();
            helpStack = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            while (!helpStack.isEmpty()) {
                mainStack.push(helpStack.pop());
            }
            mainStack.push(value);
        }

        public int deleteHead() {
            while (!mainStack.isEmpty()) {
                helpStack.push(mainStack.pop());
            }
            if (!helpStack.isEmpty()) {
                return helpStack.pop();
            } else {
                return -1;
            }
        }
    }

    class CQueue2 {

        Deque<Integer> mainStack;
        Deque<Integer> helpStack;

        public CQueue2() {
            mainStack = new ArrayDeque<>();
            helpStack = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            mainStack.push(value);
        }

        public int deleteHead() {
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
    }
}
