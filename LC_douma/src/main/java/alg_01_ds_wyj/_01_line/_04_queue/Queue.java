package alg_01_ds_wyj._01_line._04_queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 16:23
 * @Version 1.0
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
