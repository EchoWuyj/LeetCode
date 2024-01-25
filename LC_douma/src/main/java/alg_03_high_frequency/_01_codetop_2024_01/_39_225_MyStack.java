package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 23:08
 * @Version 1.0
 */
public class _39_225_MyStack {
}

// 用队列实现栈
// 核心：交换队列
class MyStack {

    private Queue<Integer> mainQueue;
    private Queue<Integer> helpQueue;

    public MyStack() {
        mainQueue = new LinkedList<>();
        helpQueue = new LinkedList<>();
    }

    public void push(int x) {
        // 新增元素 x 进入辅助队列
        // 记忆：队列辅助，栈为主
        helpQueue.offer(x);
        while (!mainQueue.isEmpty()) {
            // 主队列元素全部进入辅助队列
            helpQueue.offer(mainQueue.poll());
        }

        // 交换队列
        Queue<Integer> tmp = mainQueue;
        mainQueue = helpQueue;
        helpQueue = tmp;
    }

    public int pop() {
        return mainQueue.poll();
    }

    // 只是查看，并不弹栈
    public int top() {
        return mainQueue.peek();
    }

    public boolean empty() {
        return mainQueue.isEmpty() && helpQueue.isEmpty();
    }
}

