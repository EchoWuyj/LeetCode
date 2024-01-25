package alg_02_train_dm._20_day_数据结构设计_二刷;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 11:19
 * @Version 1.0
 */
public class _02_225_queue_to_stack2 {

    // KeyPoint 方法二 两个队列 + 交换队列
    // KeyPoint 适用场景：pop，top 调用多，push 调用少
    class MyStack {

        //   | 2 |
        //   | 7 |
        //   | 1 |
        //   |_3_|
        //   stack

        //  -------------
        //  →   →
        //  -------------
        //     mainQueue

        //  -------------
        //   →  3  →
        //  -------------
        //     helpQueue

        // mainQueue 和 helpQueue你交换

        //  -------------
        //      → 1 →
        //  -------------
        //     helpQueue

        //  -------------
        //      → 3 →
        //  -------------
        //     mainQueue => 保证栈顶元素在 mainQueue 队头

        // 将 mainQueue 中元素出队，进队到 helpQueue 中
        // mainQueue 和 helpQueue你交换

        //  -------------
        //      → 3 1 →
        //  -------------
        //     mainQueue => 保证栈顶元素在 mainQueue 队头

        //  -------------
        //      →  →
        //  -------------
        //     helpQueue

        Queue<Integer> mainQueue;
        Queue<Integer> helpQueue;

        public MyStack() {
            mainQueue = new ArrayDeque<>();
            helpQueue = new ArrayDeque<>();
        }

        // 时间复杂度：O(n)
        public void push(int x) {
            // 每次进队 queue2 (helpQueue)
            helpQueue.offer(x);
            while (!mainQueue.isEmpty()) {
                helpQueue.offer(mainQueue.poll());
            }
            // 交换队列 => 保证栈顶元素在 queue1 (mainQueue) 队头
            Queue<Integer> tmp = mainQueue;
            mainQueue = helpQueue;
            helpQueue = tmp;
        }

        // 降低 pop 和 top 时间复杂度
        // 思路：若将栈顶元素一直维持在队列队头位置，则 pop，top 操作只要对队头操作即可
        // 时间复杂度：O(1)
        public int pop() {
            return mainQueue.poll();
        }

        // 时间复杂度：O(1)
        public int top() {
            // KeyPoint ArrayDeque 底层实现 => 队列的队首(First)，对应栈的栈顶
            return mainQueue.peek();
        }

        // 时间复杂度：O(1)
        public boolean empty() {
            return mainQueue.isEmpty() && helpQueue.isEmpty();
        }
    }
}
