package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 22:05
 * @Version 1.0
 */
public class _39_232_MyQueue {

}

// 用栈实现队列
// 核心：先判断辅助栈是否为空，之后主栈元素进入辅助站
class MyQueue {

    // 定义集合类变量(包括队列和栈)，声明泛型
    private Deque<Integer> mainStack;
    private Deque<Integer> helpStack;

    public MyQueue() {
        mainStack = new ArrayDeque<>();
        helpStack = new ArrayDeque<>();
    }

    public void push(int x) {
        // 加入的新元素 x 直接加入到主栈
        // 注意：mainStack 对应 stack1
        mainStack.push(x);
    }

    public int pop() {
        // 先对辅助栈进行判空
        if (helpStack.isEmpty()) {
            // 调整操作：主栈 => 辅助栈
            while (!mainStack.isEmpty()) {
                helpStack.push(mainStack.pop());
            }
        }
        // 从辅助栈返回值，返回之前需要进行判空
        if (helpStack.isEmpty()) {
            return -1;
        } else {
            return helpStack.pop();
        }
    }

    public int peek() {
        // 辅助栈为空
        if (helpStack.isEmpty()) {
            // 不能直接返回主栈 mainStack.peek()
            // 因为 mainStack 中可能存在多个元素
            while (!mainStack.isEmpty()) {
                helpStack.push(mainStack.pop());
            }
            // if else 上下代码，最后返回的都是 peek();
            return helpStack.peek();
        } else {
            // 从辅助栈返回值
            return helpStack.peek();
        }
    }

    public boolean empty() {
        return helpStack.isEmpty() && mainStack.isEmpty();
    }
}

