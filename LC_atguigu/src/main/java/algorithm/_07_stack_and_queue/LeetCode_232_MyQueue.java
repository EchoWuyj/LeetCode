package algorithm._07_stack_and_queue;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-03-19 20:11
 * @Version 1.0
 */

//用两个栈实现队列:入队时翻转
public class LeetCode_232_MyQueue {
    //定义两个栈
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    private LeetCode_232_MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    //入队方法
    private void push(int x) {
        //1.首先将stack1中所有元素弹出,压入stack2
        //KeyPoint 加深对while循环的理解:在!stack1.isEmpty()条件下一直执行
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        //2.将新元素压入stack1
        stack1.push(x);

        //3.再将stack2中所有元素弹出，压入stack
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    //出队方法
    private int pop() {
        return stack1.pop();
    }

    //获取队首元素
    private int peek() {
        return stack1.peek();
    }

    //判空
    private boolean empty() {
        return stack1.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode_232_MyQueue myQueue = new LeetCode_232_MyQueue();
        //KeyPoint 这两个push操作是没有返回值的,所以不用System.out.println的
        myQueue.push(1); //queue is: [1]
        myQueue.push(2); //queue is: [1, 2] (leftmost is front of the queue)

        //KeyPoint 虽然是有返回值,但是没有在控制台输出打印,所以没有在控制台看到
        System.out.println(myQueue.peek()); //return 1
        System.out.println(myQueue.pop()); //return 1, queue is [2]
        System.out.println(myQueue.pop()); //return 2
        System.out.println(myQueue.empty()); //return false
    }
}
