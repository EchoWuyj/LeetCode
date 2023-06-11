package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:14
 * @Version 1.0
 */
public class _04_622_design_circular_queue {
    /*
       622. 设计循环队列
       循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则
       并且队尾被连接在队首之后以形成一个循环，它也被称为"环形缓冲器"。

       循环队列的一个好处是我们可以利用这个队列之前用过的空间。
       在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。
       但是使用循环队列，我们能使用这些空间去存储新的值。

       你的实现应该支持如下操作：
       MyCircularQueue(k): 构造器，设置队列长度为 k 。
       Front: 从队首获取元素。如果队列为空，返回 -1 。
       Rear: 获取队尾元素。如果队列为空，返回 -1 。
       enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
       deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
       isEmpty(): 检查循环队列是否为空。
       isFull(): 检查循环队列是否已满。



       示例：
       MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
       circularQueue.enQueue(1);   // 返回 true
       circularQueue.enQueue(2);   // 返回 true
       circularQueue.enQueue(3);   // 返回 true
       circularQueue.enQueue(4);   // 返回 false，队列已满
       circularQueue.Rear();   // 返回 3
       circularQueue.isFull();   // 返回 true
       circularQueue.deQueue();   // 返回 true
       circularQueue.enQueue(4);   // 返回 true
       circularQueue.Rear();   // 返回 4


       提示：
       所有的值都在 0 至 1000 的范围内；
       操作数将在 1 至 1000 的范围内；
       请不要使用内置的队列库。

    */

    class MyCircularQueue {
        int[] data;
        int head;
        int tail;

        public MyCircularQueue(int k) {
            data = new int[k + 1];
            head = tail = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            data[tail] = value;
            tail = (tail + 1) % data.length;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            // 这里不需要返回出队的元素，head 指针往前走即可，
            // data[head] 也不好设置为 null，因为 data 是不是引用数据类型
            head = (head + 1) % data.length;
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return data[head];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            // tail 指针前一个元素就是队列的最后一个元素
            // 需要考虑 tail = 0 的情况，避免 tail = -1
            int index = tail > 0 ? tail - 1 : data.length - 1;

            // index 不用再对 data.length 取余，因为 tail 在更新时，已经保证对 data.length 取余
//            return data[index % data.length];
            return data[index % data.length];
        }

        public boolean isEmpty() {
            return head == tail;
        }

        public boolean isFull() {
            // tail 循环一周，到了和 head 差 1 个元素位置，即队列满了
            return (tail + 1) % data.length == head;
        }
    }
}
