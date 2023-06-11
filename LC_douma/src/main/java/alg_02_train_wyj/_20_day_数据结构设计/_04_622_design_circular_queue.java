package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 12:44
 * @Version 1.0
 */
public class _04_622_design_circular_queue {

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
            head = (head + 1) % data.length;
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return data[head];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            int index = (tail > 0) ? tail - 1 : data.length - 1;
            return data[index];
        }

        public boolean isEmpty() {
            return tail == head;
        }

        public boolean isFull() {
            return (tail + 1) % data.length == head;
        }
    }
}
