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
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack() {
            queue1 = new ArrayDeque<>();
            queue2 = new ArrayDeque<>();
        }

        public void push(int x) {
            queue1.offer(x);
        }

        public int pop() {
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    int x = queue1.poll();
                    if (queue1.isEmpty()) return x;
                    queue2.offer(x);
                }
            } else {
                while (!queue2.isEmpty()) {
                    int x = queue2.poll();
                    if (queue2.isEmpty()) return x;
                    queue1.offer(x);
                }
            }
            throw new RuntimeException("队列为空");
        }

        public int top() {
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    int x = queue1.poll();
                    queue2.offer(x);
                    if (queue1.isEmpty()) return x;
                }
            } else {
                while (!queue2.isEmpty()) {
                    int x = queue2.poll();
                    queue1.offer(x);
                    if (queue2.isEmpty()) return x;
                }
            }
            throw new RuntimeException("队列为空");
        }

        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    class MyStack1 {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack1() {
            queue1 = new ArrayDeque();
            queue2 = new ArrayDeque<>();
        }

        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }

            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
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
