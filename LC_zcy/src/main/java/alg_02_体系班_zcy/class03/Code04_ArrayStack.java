package alg_02_体系班_zcy.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 10:05
 * @Version 1.0
 */
public class Code04_ArrayStack {

    // 栈和队列的常见面试题
    // 怎么用数组实现不超过固定大小的队列和栈？
    // 栈：正常使用
    // 队列：环形数组

    // 使用数组实现栈
    public static class ArrayStack {
        private int maxSize;
        private int top = -1;
        private int[] stack;

        // 构造器初始化一个栈
        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
        }

        // 判断栈满
        // KeyPoint 类中自己的方法是不能加上static的
        public boolean isFull() {
            return top == maxSize - 1;
        }

        // 判断栈空
        public boolean isEmpty() {
            return top == -1;
        }

        // 入栈
        public void push(int data) {
            // 先判断栈是否满
            if (isFull()) {
                // 抛出异常
                throw new RuntimeException("栈满");
            }
            stack[++top] = data;
        }

        // 出栈
        public int pop() {
            // 先判断栈是否为空
            if (isEmpty()) {
                // 抛出异常
                throw new RuntimeException("栈空");
            }
            // 先将值返回,再将top自减
            return stack[top--];
        }

        // 遍历栈
        public void list() {
            for (int i = top; i >= 0; i--) {
                // 先进后出
                System.out.print(stack[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        System.out.println(stack.isEmpty()); // true
        // 进栈操作没有任何输出显示
        stack.push(3);
        stack.push(5);
        stack.push(9);
        stack.push(2);
        stack.push(4);
        System.out.println(stack.isFull()); // true
        System.out.println(stack.pop()); // 4
        System.out.println(stack.pop()); // 2
        stack.list(); // 9,5,3
    }
}
