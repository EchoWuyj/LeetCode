package alg_02_体系班_zcy.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 23:17
 * @Version 1.0
 */
public class Code04_RingArray {

    // 使用数组来实现队列
    // 使用循环数组实现

    public static class MyQueue {
        private int[] arr;
        // 队尾
        private int pushIndex;
        // 队头
        private int pollIndex;

        // 通过size实现pushIndex和pollIndex解耦
        // 此时不要再关心pushIndex和pollIndex的变化关系

        // 队列实际装的元素个数
        private int size;
        // 队列容量大小
        private final int limit;

        // 初始化的过程(将各个变量赋初值)
        public MyQueue(int limit) {
            arr = new int[limit];
            // 表示初始位置
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            // 判断队列是否满了
            if (isFull()) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        public int pop() {
            // 判断队列是否为空
            if (isEmpty()) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return ans;
        }

        // 如果现在的下标是i,返回下一个位置
        // 两个指针一直往前走的,故两个index都是调用这个方法
        private int nextIndex(int i) {
            // i=limit-1,达到索引最大值,手动设置为0,再从头开始
            // 不能使用i++来代替i+1,因为是将i返回之后,i才是自增的,此时自增已经没有什么用了
            return i < limit - 1 ? i + 1 : 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void printQueue() {
            if (isEmpty()) {
                return;
            }

            while (!isEmpty()) {
                System.out.print(pop() + " ");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            MyQueue queue = new MyQueue(5);

            // 进队元素
            queue.push(1);
            queue.push(2);
            queue.push(3);
            queue.push(4);
            queue.push(5);

            // 同一类中的方法,可以不通过引用进行调用,但是不同类之间方法调用需要引用的
            queue.printQueue();
        }
    }
}

