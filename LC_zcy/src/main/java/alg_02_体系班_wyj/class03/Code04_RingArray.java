package alg_02_体系班_wyj.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-15 18:20
 * @Version 1.0
 */
public class Code04_RingArray {

    // 使用数组来实现队列
    public static class MyQueue {
        private int[] arr;
        private int pushIndex;
        private int pollIndex;
        private int size;
        // 限制队列的长度
        private final int limit;

        public MyQueue(int limit) {
            this.limit = limit;
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
            arr = new int[limit];
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了,不能再加了");
            }
            size++;
            arr[pushIndex] = value;
            pushIndex = updateIndex(pushIndex);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列已经空了");
            }
            size--;
            int ans = arr[pollIndex];
            pollIndex = updateIndex(pollIndex);
            return ans;
        }

        public boolean isFull() {
            return size == limit;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int updateIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }

        public void printQueue() {
            if (isFull()) {
                return;
            }

            while (!isEmpty()) {
                System.out.print(pop() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);

        queue.printQueue();
    }
}
