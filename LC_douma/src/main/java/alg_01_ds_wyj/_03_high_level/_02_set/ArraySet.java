package alg_01_ds_wyj._03_high_level._02_set;

import alg_01_ds_wyj._01_line._01_array.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 11:51
 * @Version 1.0
 */
public class ArraySet<E> implements Set<E> {
    private ArrayList<E> data;

    public ArraySet() {
        data = new ArrayList<>();
    }

    @Override
    public int size() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void add(E e) {
        if (!data.contains(e)) {
            data.addLast(e);
        }
    }

    @Override
    public void remove(E e) {
        data.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public String toString() {
        return "ArraySet { " +
                "data = " + data.toString() +
                " } ";
    }

    public static void main(String[] args) {
        Set<Integer> set = new ArraySet<>();
        set.add(2);
        set.add(4);
        set.add(1);

        System.out.println(set); // ArraySet { data = Array: size = 3, capacity = 15, [2,4,1] }

        set.add(2);
        System.out.println(set); // ArraySet { data = Array: size = 3, capacity = 15, [2,4,1] }

        System.out.println(set.contains(4)); // true

        set.remove(1);
        System.out.println(set); // ArraySet { data = Array: size = 2, capacity = 15, [2,4] }
    }
}
