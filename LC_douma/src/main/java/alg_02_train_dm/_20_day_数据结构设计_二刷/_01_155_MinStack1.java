package alg_02_train_dm._20_day_数据结构设计_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 16:12
 * @Version 1.0
 */

public class _01_155_MinStack1 {

     /*
        155. 最小栈
        设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

        实现 MinStack 类:
        MinStack() 初始化堆栈对象。
        void push(int val) 将元素 val 推入堆栈。
        void pop() 删除堆栈顶部的元素。
        int top() 获取堆栈顶部的元素。
        int getMin() 获取堆栈中的最小元素。

        示例 1:
        输入：
        ["MinStack","push","push","push","getMin","pop","top","getMin"]
        [[],[-2],[0],[-3],[],[],[],[]]

        输出：
        [null,null,null,null,-3,null,0,-2]

        解释：
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.getMin();   --> 返回 -2.

        提示：
        -2^31 <= val <= 2^31 - 1
        pop、top 和 getMin 操作总是在 非空栈 上调用
        push, pop, top, and getMin最多被调用 3 * 10^4 次

     */

    // KeyPoint 方法一 遍历 stack 比较获取最小值
    class MinStack1 {

        // 推荐使用 Deque<Integer> stack;
        Deque<Integer> stack;

        public MinStack1() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
        }

        public void pop() {
            // pop 弹栈，有返回 E
            // 只是这里返回值为 void，故没有 return
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        // O(n) => 不符合在'常数时间内检索到最小元素的栈'
        public int getMin() {

            // KeyPoint 错误写法：不能将 stack 中元素弹栈，否则影响后续操作
            // min = Math.min(min, stack.poll());

            // 将栈顶元素，默认设置为最小
            // 避免 int min = Integer.MAX_VALUE;
            int min = stack.peek();

            // 直接将 stack 看成普通集合，使用 for 循环遍历
            for (int i : stack) {
                min = Math.min(min, i);
            }
            return min;
        }
    }
}
