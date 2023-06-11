package alg_02_体系班_wyj.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-15 17:58
 * @Version 1.0
 */
public class Code04_ArrayStack {

    // 数组实现栈
    public static class ArrayStack {
        public int maxSize;
        public int top;
        public int[] stack;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            top = -1;
            stack = new int[maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        // 入栈
        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("栈已满");
            }

            stack[++top] = value;
        }

        // 出栈
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈已空");
            }
            return stack[top--];
        }

        // 遍历
        public void listStack() {
            if (isEmpty()) {
                return;
            }

            while (!isEmpty()) {
                System.out.println(pop());
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
        System.out.println("============================");
        stack.listStack(); // 9,5,3
    }
}
