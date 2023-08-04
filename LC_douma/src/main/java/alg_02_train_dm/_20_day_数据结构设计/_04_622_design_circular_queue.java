package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:14
 * @Version 1.0
 */
public class _04_622_design_circular_queue {
    /*
       622. 设计循环队列
       循环队列是一种线性数据结构，其操作表现基于 FIFO(先进先出) 原则
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

        //  56 12 33 24 45 _
        //  ↑              ↑
        // head           tail

        // 出队：只是移动 head 指针
        //  56 12 33 24 45 _
        //      ↑            ↑
        //     head         tail

        // 进队：11 元素进队
        //  56 12 33 24 45 11 _
        //      ↑             ↑
        //     head          tail

        int[] data;
        // KeyPoint 关键：移动指针，而不是移动数据，从而降低时间复杂度：O(n) -> O(1)
        // head 指向队首元素
        int head;
        // tail 指向队尾元素后面一个空的位置
        int tail;

        public MyCircularQueue(int k) {
            // + 1 表示空余位置，用来区分队列是空还是满
            data = new int[k + 1];
            head = tail = 0;
        }

        // 入队
        public boolean enQueue(int value) {

            // 入队判断是否满
            if (isFull()) return false;
            // 本来 tail 就是指向最后一个元素的后一个位置
            data[tail] = value;
            tail = (tail + 1) % data.length;
            return true;
        }

        // 出队
        public boolean deQueue() {
            //出队判断是否空
            if (isEmpty()) return false;
            // 1.这里不需要返回出队的元素，head 指针往前走即可
            // 2.data[head] 也不好设置为 null，因为 data 是不是引用数据类型，后续值会对其进行覆盖
            head = (head + 1) % data.length;
            return true;
        }

        // 返回队头
        public int Front() {
            // -1 表示不存在 => 所有的值都在 0 至 1000 的范围内
            if (isEmpty()) return -1;
            return data[head];
        }

        // 返回队尾
        public int Rear() {
            // -1 表示不存在 => 所有的值都在 0 至 1000 的范围内
            if (isEmpty()) return -1;
            // 1.tail 指针前一个元素就是队列的最后一个元素
            // 2.需要考虑 tail = 0 的情况，避免 tail = -1，数据越界
            // KeyPoint 易错点
            // 返回队尾，使用 int index 接受，而不能使用 tail 指针接受，否则修改了 tail
            // 因为返回队尾，只是一个查询操作，并没有元素入队，tail 指针位置不需要移动
            int index = tail > 0 ? tail - 1 : data.length - 1;

            // index 不用再对 data.length 取余，因为 tail 在更新时，已经保证对 data.length 取余
            // return data[index % data.length];
            return data[index];
        }

        // 是否为空
        public boolean isEmpty() {
            // 规定 head 指针和 tail 指针在同一个位置表示为队列为空
            return head == tail;
        }

        public boolean isFull() {
            // tail 循环一周，到了和 head 差 1 个元素位置，即队列满了
            //  _  _  _  _  _  _
            //     ↑     ↑
            //    tail  head
            // length = 4，tail 最多为 3，tail 再后移，即为 (tail+1) % 4 = 0 回到原点
            return (tail + 1) % data.length == head;
        }
    }
}
