package alg_03_high_frequency._01_codetop.top_100;

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

    // 定义集合类变量都是需要声明泛型(队列和栈)
    // 凡是集合类都是需要声明泛型
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

    // KeyPoint 注意：本质上 pop 操作 和 peek 操作实现思路是一样的
    public int pop() {
        // 辅助栈作为队头位置，先对辅助栈进行判空
        if (helpStack.isEmpty()) {
            // 调整操作：主栈 => 辅助栈
            while (!mainStack.isEmpty()) {
                helpStack.push(mainStack.pop());
            }
        }

        // 返回辅助栈元素 => 返回队头元素
        // 从辅助栈返回值，返回之前需要进行判空
        if (helpStack.isEmpty()) {
            // x 范围：1 <= x <= 9
            return -1;
        } else {
            return helpStack.pop();
        }
    }

    public int peek() {
        // 返回辅助栈元素 => 返回队头元素
        // 需要判断辅助栈为空
        // 1.辅助栈为空，将主栈元素移动到辅助栈，再去从辅助栈返回值
        //   不能直接返回主栈 mainStack.peek()，因为主栈中可能存在多个元素
        if (helpStack.isEmpty()) {
            while (!mainStack.isEmpty()) {
                helpStack.push(mainStack.pop());
            }
            // 多加个判空，避免程序出错
            // 题目限制：假设所有操作都是有效的
            if (helpStack.isEmpty()) {
                return -1;
            } else {
                // if else 上下代码，最后返回的都是 peek();
                return helpStack.peek();
            }
        } else {
            // 2.辅助栈不为空，从辅助栈返回值
            return helpStack.peek();
        }
    }

    public boolean empty() {
        return helpStack.isEmpty() && mainStack.isEmpty();
    }
}

