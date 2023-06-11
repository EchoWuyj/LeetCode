package alg_01_ds_wyj._03_high_level._02_set;

import alg_01_ds_dm._01_line._02_linkedlist.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 11:56
 * @Version 1.0
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> data;

    public LinkedListSet() {
        data = new LinkedList<>();
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
            data.addFirst(e);
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
        return "LinkedListSet { " +
                "data = " + data.toString() +
                " } ";
    }

    public static void main(String[] args) {
        Set<Integer> set = new LinkedListSet<>();
        set.add(2);
        set.add(4);
        set.add(1);
        System.out.println(set); // LinkedListSet { data = 1 => 4 => 2 => null }
        set.add(2);
        System.out.println(set); // LinkedListSet { data = 1 => 4 => 2 => null }
        System.out.println(set.contains(4));  // true
        set.remove(1);
        System.out.println(set); // LinkedListSet { data = 4 => 2 => null }
    }
}
