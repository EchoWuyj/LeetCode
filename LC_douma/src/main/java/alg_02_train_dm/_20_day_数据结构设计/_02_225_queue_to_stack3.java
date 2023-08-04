package alg_02_train_dm._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 11:19
 * @Version 1.0
 */
public class _02_225_queue_to_stack3 {

    // KeyPoint 方法三 一个队列 + 自身循环进队
    class MyStack {
        Queue<Integer> queue;

        public MyStack() {
            queue = new ArrayDeque<>();
        }

        // 时间复杂度：O(n)
        public void push(int x) {
            int size = queue.size();
            queue.offer(x);
            // 自身循环进队
            // 将 x 元素之前所有的元素，重新再进队，从而保证 x 是队首元素
            for (int i = 0; i < size; i++) {
                queue.offer(queue.poll());
            }
        }

        // 时间复杂度：O(1)
        public int pop() {
            return queue.poll();
        }

        // 时间复杂度：O(1)
        public int top() {
            return queue.peek();
        }

        // 时间复杂度：O(1)
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
