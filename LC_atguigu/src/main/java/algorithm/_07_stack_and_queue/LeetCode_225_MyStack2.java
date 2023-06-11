package algorithm._07_stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-03-19 20:00
 * @Version 1.0
 */

//用一个队列实现自定义栈
public class LeetCode_225_MyStack2 {
    //定义一个队列
    Queue<Integer> queue;

    public LeetCode_225_MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        //1.首先记录当前队列长度
        int l = queue.size();

        //2.将x入队
        queue.offer(x);

        //3.把queue中原先的所有元素依次出队,然后再入队
        for (int i = 0; i < l; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
