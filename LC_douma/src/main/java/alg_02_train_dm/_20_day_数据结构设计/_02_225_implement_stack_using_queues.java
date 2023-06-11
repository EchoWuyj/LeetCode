package alg_02_train_dm._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:09
 * @Version 1.0
 */
public class _02_225_implement_stack_using_queues {
     /* 
        225. 用队列实现栈
        请你仅使用两个队列实现一个后入先出 (LIFO )的栈，
        并支持普通栈的全部四种操作 (push、top、pop 和 empty)。

        实现 MyStack 类：
        void push(int x) 将元素 x 压入栈顶。
        int pop() 移除并返回栈顶元素。
        int top() 返回栈顶元素。
        boolean empty() 如果栈是空的，返回 true；否则，返回 false 。

        输入：
        ["MyStack", "push", "push", "top", "pop", "empty"]
        [[], [1], [2], [], [], []]
        输出：
        [null, null, null, 2, 2, false]

        解释：
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.top(); // 返回 2
        myStack.pop(); // 返回 2
        myStack.empty(); // 返回 False

        提示：
        1 <= x <= 9
        最多调用 100 次 push、pop、top 和 empty
        KeyPoint 注意点：方法调用 100 次，对应数据规模为 100
        每次调用 pop 和 top 都保证栈不为空

        注意：
        1. 你只能使用队列的基本操作：也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
        2. 你所使用的语言也许不支持队列。
           你可以使用 list  (列表 )或者 deque (双端队列 )来模拟一个队列,只要是标准的队列操作即可

     */

    // KeyPoint 方法一 两个队列 + 交替进队
    // MyStack1 适用：push 调用多，pop，top 调用少
    class MyStack1 {

        // 逻辑理解方向，实际底层反向

        // queue1 -> 队尾  ->  队首 ->
        // stack  栈顶         栈底
        // queue2 -> 队尾  ->  队首 ->

        // 主队列
        private Queue<Integer> queue1;
        // 辅助队列
        private Queue<Integer> queue2;

        public MyStack1() {
            // 构造方法初始化：数据结构
            queue1 = new ArrayDeque<>();
            queue2 = new ArrayDeque<>();
        }

        // 时间复杂度：O(1)
        public void push(int x) {
            queue1.offer(x);
        }

        // 时间复杂度：O(n)
        public int pop() {
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    // poll 出队，ll 像两条排满人的长队
                    Integer x = queue1.poll();
                    // 若 queue1 为空，则 x 为队尾元素，直接返回即可，x 不用再进队 queue2
                    if (queue1.isEmpty()) return x;
                    queue2.offer(x);
                }
            } else if (!queue2.isEmpty()) {
                while (!queue2.isEmpty()) {
                    Integer x = queue2.poll();
                    if (queue2.isEmpty()) return x;
                    queue1.offer(x);
                }
            }
            // 抛出异常，为了保证程序编译通过，该行代码是肯定不会执行
            throw new RuntimeException("没有数据");
        }

        // 时间复杂度：O(n)
        public int top() {
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    Integer x = queue1.poll();
                    // 不管是否为栈顶元素，统统进队 queue2
                    queue2.offer(x);
                    if (queue1.isEmpty()) return x;
                }
            } else if (!queue2.isEmpty()) {
                while (!queue2.isEmpty()) {
                    Integer x = queue2.poll();
                    queue1.offer(x);
                    if (queue2.isEmpty()) return x;
                }
            }
            throw new RuntimeException("没有数据");
        }

        // 时间复杂度：O(1)
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    // KeyPoint 方法二 两个队列 + 交换队列
    // 将栈顶元素一直维持在队列队头位置，pop，top 操作只要对队头操作即可
    // MyStack2 适用：pop，top 调用多，push 调用少
    class MyStack2 {

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public MyStack2() {
            queue1 = new ArrayDeque<>();
            queue2 = new ArrayDeque<>();
        }

        // 时间复杂度：O(n)
        public void push(int x) {
            // 每次进队 queue2
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            // 交换队列 => 保证栈顶元素在 queue1 队头
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        // 时间复杂度：O(1)
        public int pop() {
            return queue1.poll();
        }

        // 时间复杂度：O(1)
        public int top() {
            return queue1.peek();
        }

        // 时间复杂度：O(1)
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    // KeyPoint 方法三 一个队列 + 循环进队
    class MyStack {
        private Queue<Integer> queue;

        public MyStack() {
            queue = new ArrayDeque<>();
        }

        // 时间复杂度：O(n)
        public void push(int x) {
            int size = queue.size();
            queue.offer(x);
            // 自身循环进队
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
