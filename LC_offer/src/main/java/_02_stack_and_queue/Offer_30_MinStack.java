package _02_stack_and_queue;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-08-25 13:31
 * @Version 1.0
 */
public class Offer_30_MinStack {
    public static void main(String[] args) {

    }

    public static class MinStack {
        // KeyPoint  补充 Java Stack 操作
        // Java中stack的操作(这两个操作都是有返回值的)
        // pop() E 移除堆栈顶部的对象，并作为此函数的值返回该对象
        // peek() E 查看堆栈顶部的对象，但不从堆栈中移除它(只是看一下栈顶元素)

        // KeyPoint 创建两个栈

        // 创建栈 stack1 ，用来作为数据栈
        Stack<Integer> stack1;

        // 创建栈 stack2 ，用来作为辅助栈
        // stack2 存储 stack1 中所有【非严格降序】的元素
        //      这意味着 stack2 中的【栈顶元素】是 stack1 中的【最小元素】，维护好 stack2 和 stack1 的这种关系
        //      那么 min() 函数只需返回 stack2 的栈顶元素即可，并且时间复杂度为 O(1)
        Stack<Integer> stack2;

        public MinStack() {
            // 初始化 stack1
            stack1 = new Stack<Integer>();

            // 初始化 stack2
            stack2 = new Stack<Integer>();
        }

        // 这个函数是最小栈的压入操作
        public void push(int x) {
            // 数据栈 stack1 直接压入 x
            stack1.push(x);

            // 如果辅助栈 stack2 是空，可以直接压入 x
            // 此时，由于只有一个元素，stack2 中的【栈顶元素】是 stack1 中的【最小元素】
            if (stack2.isEmpty()) {
                // stack2 直接压入 x
                stack2.push(x);

                // 如果辅助栈 stack2 不为空
                // 那么考虑是否把 x 压入到辅助栈 stack2 中，目的是为了维护 stack2 和 stack1 的关系
                // 让 stack2 中的【栈顶元素】是 stack1 中的【最小元素】
            } else {

                // 如果辅助栈 stack2 的栈顶元素大于或者等于 x ，那么可以把 x 压入到 stack2 中
                // 这样做，stack2 中的【栈顶元素】还是 stack1 中的【最小元素】
                if (stack2.peek() >= x) {
                    stack2.push(x);
                } else {
                    stack2.push(stack2.peek());
                }
            }
        }

        // 这个函数是最小栈的弹出操作(没有返回值)
        // KeyPoint 区别Java中Stack的pop操作
        public void pop() {
            // 数据栈 stack1 直接 pop
            stack1.pop();
            // KeyPoint 为了保证 stack1 和 stack2 两个栈中的元素个数是一致的
            //  这样才能保证 stack2 中保存的元素是 stack1 中元素的最小值
            // 辅助栈 stack2 直接 pop
            stack2.pop();
        }

        // 这个函数是获取最小栈的栈顶元素操作
        public int top() {
            // 返回数据栈 stack1 的栈顶元素
            // KeyPoint 只是的返回元素值,但是栈中元素位置没有变化
            return stack1.peek();
        }

        // 这个函数是获取最小栈的最小元素操作
        public int min() {
            // 由于 stack2 中的【栈顶元素】是 stack1 中的【最小元素】
            // 所以，返回 stack2 的栈顶元素就是全部元素的最小值
            return stack2.peek();
        }
    }
}
