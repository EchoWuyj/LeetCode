package alg_01_ds_dm._01_line._04_queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 11:17
 * @Version 1.0
 */

// 多种数据结构实现队列，所以将 Queue 定义成接口
public interface Queue<E> {
    /**
     * 查询队列中的元素个数
     */
    int getSize();

    /**
     * 判断队列是否为空
     */
    boolean isEmpty();

    /**
     * 入队
     */
    void enqueue(E e);

    /**
     * 出队
     */
    E dequeue();

    /**
     * 查看队首的元素
     */
    E getFront();
}
