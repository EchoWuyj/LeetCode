package _02_stack_and_queue;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-08-21 18:38
 * @Version 1.0
 */
public class Offer_09_CQueue {
    public static void main(String[] args) {

    }

    public static class CQueue {
        // Java 中的 Stack 类设计有问题，大部分情况下是使用 LinkedList
        // 来构建栈，但为了结合动画更好的理解这道题目，所以依旧使用 Stack

        // 队列的特点，先进先出；栈的特点，先进后出

        // Java中stack的操作(这两个操作都是有返回值的)
        // pop() E 移除堆栈顶部的对象，并作为此函数的值返回该对象
        // peek() E 查看堆栈顶部的对象，但不从堆栈中移除它

        // KeyPoint 创建两个栈，需要定义在类中方法外，后续方法中需要调用这两个 Stack
        // 创建栈 stack1 用来充当队列
        Stack<Integer> stack1;
        // 创建栈 stack2 用来辅助 stack1 执行队列的一些复杂操作
        Stack<Integer> stack2;

        // 这个函数是 create queue 意思就是初始化队列
        // 由于题目要求我们用两个栈实现队列，所以在这个函数中初始化的是两个栈
        public CQueue() {
            // 初始化 stack1
            stack1 = new Stack<>();
            // 初始化 stack2
            stack2 = new Stack<>();
        }

        // 这个函数的意思是在队列的尾部添加元素
        // 使用栈来完成这个操作的话就是在 stack1 的后面添加元素就行
        public void appendTail(int value) {
            stack1.push(value);
        }

        // 这个函数的意思是在队列的头部删除元素
        // 由于队列的特点是先进先出，所以需要借助 stack1 和 stack2 去定位到之前存储进来的元素
        public int deleteHead() {

            // 1、如果 stack2 栈不为空，说明 stack2 里面已经存储了一些元素，
            // 并且 stack2 的栈顶元素就是两个栈中最早加入的元素
            if (!stack2.isEmpty()) {
                // 返回 stack2 的栈顶元素，满足了队列先进先出的特点
                // KeyPoint 需要将 stack2.pop() 进行返回
                //  需要有根据错误代码提示，来调试代码的能力
                return stack2.pop();
            }

            // 2、如果 stack2 为空，并且发现 stack1 也为空，
            // 说明 stack1 和 stack2 构建的队列中没有元素，
            // KeyPoint 此处的 if 是在上个 if 判断条件下，叠加的
            //  if 判断，此时判断的条件不是一个 if，而是两个 if
            if (stack1.isEmpty()) {
                // 根据题意，直接返回 -1
                return -1;
            }

            // 3、如果 stack2 为空，但 stack1 不为空
            // 那么需要先将 stack1 中的元素依次【倒序】放入 stack2 中
            //      对于 stack1 来说，越早加入的元素在【栈底】，越晚加入的元素在【栈顶】
            //      由于队列是【先进先出】，所以删除的应该是 stack1 的【栈底】元素
            while (!stack1.isEmpty()) {
                // 获取 stack1 的栈顶元素并将该元素从 stack1 中弹出
                int topValue = stack1.pop();
                // 把该元素加入到 stack2
                // 这样 stack2 的栈顶元素就是 stack1 的栈底元素
                stack2.push(topValue);
            }

            // 4、返回 stack2 的栈顶元素，满足了队列先进先出的特点
            return stack2.pop();
        }
    }
}
