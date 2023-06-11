package alg_01_ds_wyj._03_high_level._02_set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 14:41
 * @Version 1.0
 */
public class HashSet<E> implements Set<E> {

    private E[] data;
    private int size;

    public HashSet() {
        data = (E[]) new Object[10];
        this.size = 0;
    }

    private int hash(E e, int length) {
        return Math.abs(e.hashCode()) % length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) {
            data[index] = e;
            size++;
            if (size == data.length) {
                resize(2 * data.length);
            }
        }
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                int newIndex = hash(data[i], newCapacity);
                newData[newIndex] = data[i];
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) {
        int index = hash(e, data.length);
        if (data[index] != null) {
            data[index] = null;
            size--;
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e, data.length);
        return data[index] != null;
    }
}
