package alg_01_ds_wyj._01_line._03_stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 13:48
 * @Version 1.0
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
