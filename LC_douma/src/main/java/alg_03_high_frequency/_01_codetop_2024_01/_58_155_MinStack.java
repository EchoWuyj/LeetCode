package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 23:04
 * @Version 1.0
 */
public class _58_155_MinStack {
}

class MinStack {
    // 定义两个栈
    Deque<Integer> dataStack;
    Deque<Integer> minStack;

    public MinStack() {
        // 赋值操作，只是引用，不能再有数据类型 Deque<Integer>
        dataStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        dataStack.push(val);
        // minStack 同步更新
        // 注意：判断条件是 || 的关系，不是 && 的关系
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        // 使用 pop 弹出
        int top = dataStack.pop();
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
