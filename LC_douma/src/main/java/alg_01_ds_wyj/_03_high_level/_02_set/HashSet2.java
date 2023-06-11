package alg_01_ds_wyj._03_high_level._02_set;

import java.io.DataInput;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 14:52
 * @Version 1.0
 */
public class HashSet2<E> implements Set<E> {

    private class Item<E> {
        E data;
        boolean isDeleted;

        public Item(E data) {
            this.data = data;
            this.isDeleted = false;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(E e) {

    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return false;
    }
}
