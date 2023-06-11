package alg_01_ds_dm._03_high_level._02_set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 23:16
 * @Version 1.0
 */
public interface Set<E> {
    int size();

    boolean isEmpty();

    void add(E e);

    void remove(E e);

    boolean contains(E e);
}
