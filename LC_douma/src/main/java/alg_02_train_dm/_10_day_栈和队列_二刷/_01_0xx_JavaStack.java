package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 19:46
 * @Version 1.0
 */
public class _01_0xx_JavaStack {

    public static void main(String[] args) {

        // 循环队列 => 双端队列 => 实现栈
        // Deque<Integer> stack = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);

        // 拿到栈顶元素(没有弹栈)
        System.out.println(stack.peek()); // 2

        // 判断栈是否为空
        System.out.println(stack.isEmpty());

        // 弹出栈顶元素，并返回栈顶元素
        Integer top = stack.pop();

        // 队列
        Queue<Integer> queue = new LinkedList<>();

        // 双端队列
        Deque<Integer> deque = new LinkedList<>();

        // 入队
        deque.offer(2);
        // 出队
        deque.poll();

        // First => 队首(队头)
        // Last => 队尾(队尾)

        // 队首入队
        deque.offerFirst(1);
        // 队尾入队
        deque.offerLast(3);

        // 队首出队
        deque.pollFirst();
        // 队尾出队
        deque.pollLast();
    }
}
