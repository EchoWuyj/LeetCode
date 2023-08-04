package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:36
 * @Version 1.0
 */
public class _02_225_implement_stack_using_queues {

    class MyStack {

        Queue<Integer> mainQueue;
        Queue<Integer> helpQueue;

        public MyStack() {
            mainQueue = new ArrayDeque<>();
            helpQueue = new ArrayDeque<>();
        }

        public void push(int x) {
            mainQueue.offer(x);
        }

        public int pop() {
            if (!mainQueue.isEmpty()) {
                while (!mainQueue.isEmpty()) {
                    int num = mainQueue.poll();
                    if (mainQueue.isEmpty()) return num;
                    helpQueue.offer(num);
                }
            } else {
                if (!helpQueue.isEmpty()) {
                    while (!helpQueue.isEmpty()) {
                        int num = helpQueue.poll();
                        if (helpQueue.isEmpty()) return num;
                        mainQueue.offer(num);
                    }
                }
            }
            throw new RuntimeException("没有数据");
        }

        public int top() {
            if (!mainQueue.isEmpty()) {
                while (!mainQueue.isEmpty()) {
                    int num = mainQueue.poll();
                    helpQueue.offer(num);
                    if (mainQueue.isEmpty()) return num;
                }
            } else {
                if (!helpQueue.isEmpty()) {
                    while (!helpQueue.isEmpty()) {
                        int num = helpQueue.poll();
                        mainQueue.offer(num);
                        if (helpQueue.isEmpty()) return num;
                    }
                }
            }
            throw new RuntimeException("没有数据");
        }

        public boolean empty() {
            return mainQueue.isEmpty() && helpQueue.isEmpty();
        }
    }

    class MyStack1 {
        Queue<Integer> mainQueue;
        Queue<Integer> helpQueue;

        public MyStack1() {
            mainQueue = new ArrayDeque<>();
            helpQueue = new ArrayDeque<>();
        }

        public void push(int x) {
            helpQueue.offer(x);
            while (!mainQueue.isEmpty()) {
                helpQueue.offer(mainQueue.poll());
            }
            Queue<Integer> tmp = mainQueue;
            mainQueue = helpQueue;
            helpQueue = tmp;
        }

        public int pop() {
            return mainQueue.poll();
        }

        public int top() {
            return mainQueue.peek();
        }

        public boolean empty() {
            return mainQueue.isEmpty() && helpQueue.isEmpty();
        }
    }

    class MyStack2 {
        Queue<Integer> queue;

        public MyStack2() {
            queue = new ArrayDeque<>();
        }

        public void push(int x) {
            int size = queue.size();
            queue.offer(x);
            for (int i = 0; i < size; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
