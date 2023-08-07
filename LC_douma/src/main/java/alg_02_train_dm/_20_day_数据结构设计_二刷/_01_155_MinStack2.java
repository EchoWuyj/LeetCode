package alg_02_train_dm._20_day_数据结构设计_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 9:48
 * @Version 1.0
 */
public class _01_155_MinStack2 {

    // KeyPoint 方法二 辅助栈 minStack
    class MinStack2 {
        Deque<Integer> dataStack;
        Deque<Integer> minStack;

        public MinStack2() {
            dataStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            dataStack.push(val);

            // KeyPoint minStack 压栈 2 种情况
            // 1.minStack 刚开始为空，遇到的第一元素压栈
            // 2.val <= minStack 栈顶
            // 注意：val = minStack 是包括的，若去掉等于，则可能会出现 dataStack 不为空
            //      但是 minStack 为空了，这样下面的 getMin 就会出现异常了
            // 3.val > minStack.peek()，则没有进入 minStack 中
            // KeyPoint 凡是边界条件，一定要好好考虑，特别容易出错
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int top = dataStack.pop();
            // top 为 minStack 的栈顶，minStack 才去 pop，否则只是 dataStack 进行 pop
            if (top == minStack.peek()) {
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
}
