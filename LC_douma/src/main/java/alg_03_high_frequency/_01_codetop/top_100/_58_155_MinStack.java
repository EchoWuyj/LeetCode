package alg_03_high_frequency._01_codetop.top_100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 23:04
 * @Version 1.0
 */
public class _58_155_MinStack {
}

// 最小栈
// 定义两个栈实现
class MinStack {
    Deque<Integer> dataStack;
    Deque<Integer> minStack;

    public MinStack() {
        // 赋值操作：只是给引用赋值，并需要再有数据类型 Deque<Integer>
        dataStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        // dataStack 压栈
        dataStack.push(val);
        // minStack 同步更新，判断条件是 || 的关系，不是 && 的关系
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        // dataStack 使用 pop 弹出
        int top = dataStack.pop();
        // minStack 也得顺便判断下，是否需要 pop 弹出
        if (minStack.peek() == top) {
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
