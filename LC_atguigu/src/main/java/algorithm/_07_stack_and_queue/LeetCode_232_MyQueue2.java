package algorithm._07_stack_and_queue;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-03-19 20:32
 * @Version 1.0
 */
public class LeetCode_232_MyQueue2 {
    //定义两个栈
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    private LeetCode_232_MyQueue2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();


    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        //1.判断stack2是否为空,如果为空,就要将stack1中所有元素弹出,然后压入
        //KeyPoint stack2中需要不停地判断是否为空,如果为空则需要将stack1的数据反转到stack2中,然后再去从stack2从pop
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        //2.弹出stack2栈顶元素
        return stack2.pop();
    }

    public int peek() {
        //1.判断stack2是否为空,如果为空,就要将stack1中所有元素弹出,然后压入
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }

        //2.返回stack2栈顶元素
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode_232_MyQueue2 myQueue = new LeetCode_232_MyQueue2();
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
