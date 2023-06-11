package alg_01_ds_wyj._03_high_level._02_set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 11:50
 * @Version 1.0
 */
public interface Set<E> {
    int size();

    boolean isEmpty();

    void add(E e);

    void remove(E e);

    boolean contains(E e);
}
