package alg_02_train_dm._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:09
 * @Version 1.0
 */
public class _02_225_queue_to_stack1 {
     /* 
        225. 用队列实现栈
        请你仅使用 两个队列 实现一个后入先出 (LIFO )的栈，
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

        注意：
        1. 你只能使用队列的基本操作：也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
        2. 你所使用的语言也许不支持队列。
           你可以使用 list  (列表 )或者 deque (双端队列 )来模拟一个队列,只要是标准的队列操作即可

        KeyPoint 补充说明
        方法调用 100 次，对应数据规模为 100
        每次调用 pop 和 top 都保证栈不为空

     */

    // KeyPoint 方法一 两个队列 + 交替进队
    // KeyPoint 适用场景：push 调用多，pop，top 调用少
    class MyStack {

        //   | 2 |
        //   | 7 |
        //   | 1 |
        //   |_3_|
        //   stack

        //  -------------
        //  →  2 7 1 3  →
        //  -------------
        //     mainQueue

        //  -------------
        //   →    →
        //  -------------
        //     helpQueue

        // mainQueue 元素不断出队，出队的元素不断进队 helpQueue

        //  -------------
        //      →  2   →
        //  -------------
        //     mainQueue

        //  -------------
        //   →  7 1 3  →
        //  -------------
        //     helpQueue

        // KeyPoint 逻辑理解方向(从左往右)，实际底层反向(从右往左)

        // 1.逻辑理解方向
        // mainQueue -> 队尾  ->  队首 ->
        // stack        栈顶      栈底
        // helpQueue -> 队尾  ->  队首 ->

        // 2.实际底层反向
        //  ← First ← ← ← Last ←
        //      ↑           ↑
        //  队首(队头)   队尾(队尾)

        // 主队列
        Queue<Integer> mainQueue;
        // 辅助队列
        Queue<Integer> helpQueue;

        public MyStack() {
            // KeyPoint 一般都是构造方法初始化数据结构，注意这种代码规范写法
            mainQueue = new ArrayDeque<>();
            helpQueue = new ArrayDeque<>();
        }

        // 时间复杂度：O(1)
        public void push(int x) {
            mainQueue.offer(x);
        }

        // 时间复杂度：O(n)
        // 功能：mainQueue 和 helpQueue 元素交替进出队
        public int pop() {
            if (!mainQueue.isEmpty()) {
                while (!mainQueue.isEmpty()) {
                    // poll 出队，ll 像两条排满人的长队
                    Integer num = mainQueue.poll();
                    // 若 queue1 为空，则 num 为队尾元素，直接返回即可，num 不用再进队 queue2
                    if (mainQueue.isEmpty()) return num;
                    helpQueue.offer(num);
                }
            } else {
                if (!helpQueue.isEmpty()) {
                    while (!helpQueue.isEmpty()) {
                        Integer num = helpQueue.poll();
                        if (helpQueue.isEmpty()) return num;
                        mainQueue.offer(num);
                    }
                }
            }
            // 实在不知道返回什么，则抛出异常
            // 为了保证程序编译通过，该行代码是肯定不会执行
            // 注意：是 throw，不是 return
            throw new RuntimeException("没有数据");
        }

        // 时间复杂度：O(n)
        public int top() {
            if (!mainQueue.isEmpty()) {
                while (!mainQueue.isEmpty()) {
                    Integer num = mainQueue.poll();
                    // 不管是否为栈顶元素，统统进队 queue2
                    // KeyPoint 注意事项
                    // offer 操作，一定在 if 判断之前，top 只是获取栈顶元素
                    // 并不是 pop，需要保证 num 不遗漏
                    helpQueue.offer(num);
                    if (mainQueue.isEmpty()) return num;
                }
            } else {
                if (!helpQueue.isEmpty()) {
                    while (!helpQueue.isEmpty()) {
                        Integer num = helpQueue.poll();
                        mainQueue.offer(num);
                        if (helpQueue.isEmpty()) return num;
                    }
                }
            }
            throw new RuntimeException("没有数据");
        }

        // 时间复杂度：O(1)
        public boolean empty() {
            return mainQueue.isEmpty() && helpQueue.isEmpty();
        }
    }
}
