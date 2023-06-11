package alg_02_体系班_zcy.class03;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 11:01
 * @Version 1.0
 */
public class Code06_TwoStacksImplementQueue {

    // 栈和队列的常见面试题
    //  1)如何用栈结构实现队列结构
    //  2)如何用队列结构实现栈结构

    // 思路:分别使用push栈和pop栈
    //  1)push栈导入数据需要一次性将数据全部导入pop栈
    //  2)不能在pop栈还有数据的情况下,导数据,即只有pop栈为空
    //    才能将push栈元素导入pop栈,之后再从pop栈弹栈元素
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            // 保证stackPop为空
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        // 虽然集合中定义的是Integer,但是实际使用形参可以使用int
        public void add(int pushInt) {
            stackPush.push(pushInt);
            // 每次加入数据之后需要判断是否能导数据
            pushToPop();
        }

        // 弹出
        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }

            pushToPop();
            return stackPop.pop();
        }

        // 获取队列队头值
        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek()); // 1
        System.out.println(test.poll()); // 1
        System.out.println(test.peek()); // 2
        System.out.println(test.poll()); // 2
        System.out.println(test.peek()); // 3
        System.out.println(test.poll()); // 3
    }
}
