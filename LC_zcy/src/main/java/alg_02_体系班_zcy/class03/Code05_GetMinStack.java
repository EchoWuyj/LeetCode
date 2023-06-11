package alg_02_体系班_zcy.class03;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 10:46
 * @Version 1.0
 */
public class Code05_GetMinStack {

    // 栈和队列的常见面试题
    // 实现一个特殊的栈,在基本功能的基础上,再实现返回栈中最小元素的功能
    // 1)pop,push,getMin操作的时间复杂度都是O(1)
    // 2)设计的栈类型可以使用现成的栈结构

    // KeyPoint ava栈Stack中add与push,peek与pop的区别
    // add & push
    // 共同点:add,push都可以向stack中添加元素
    // 不同点
    // add是继承自Vector的方法,且返回值类型是boolean
    // push是Stack自身的方法,返回值类型是参数类型

    // peek & pop
    // 共同点:peek,pop都是返回栈顶元素
    // 不同点
    // peek()函数返回栈顶的元素,但不弹出该栈顶元素
    // pop()函数返回栈顶的元素,并且将该栈顶元素出栈

    //----------------------------------------------

    // KeyPoint 实现一(推荐掌握)
    // 数据栈和最小栈同步push和poll
    public static class MinStack2 {
        // 数据栈
        private Stack<Integer> stackData;
        // 最小栈
        private Stack<Integer> stackMin;

        public MinStack2() {
            // 数据栈是正常的栈,没有任何区别的栈
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            // 最小栈需要保证元素最小,故需要判断之后,再去push
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            // 数据栈直接push
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            // 同步弹出
            this.stackMin.pop();
            // 最小栈不影响数据栈,最终返回的是数据栈中的元素
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    // KeyPoint 实现二(了解)
    // 数据栈和最小栈
    // 最小栈push
    //    push操作只有push元素<=最小栈的栈顶元素,则push
    //    push元素>最小栈的栈顶,则不push
    // poll
    // 只有数据栈和最小栈的栈顶相等时弹出,不相等,则不弹出
    public static class MinStack1 {
        // KeyPoint 需要定义数据类型
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MinStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getMin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }

            int value = this.stackData.pop();
            if (value == this.getMin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {

        MinStack1 stack2 = new MinStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());

        System.out.println("=============");

        MinStack1 stack1 = new MinStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());
    }
}
