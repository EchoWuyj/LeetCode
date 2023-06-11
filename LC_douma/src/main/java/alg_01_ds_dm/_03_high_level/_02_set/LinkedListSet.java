package alg_01_ds_dm._03_high_level._02_set;

import alg_01_ds_dm._01_line._02_linkedlist.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 23:26
 * @Version 1.0
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> data;

    public LinkedListSet() {
        this.data = new LinkedList<>();
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
    public void add(E e) { // O(n)
        if (!data.contains(e)) {
            // 从链表头部添加，效率更高
            data.addFirst(e);
        }
    }

    @Override
    public void remove(E e) { // O(n)
        data.removeElement(e);
    }

    @Override
    public boolean contains(E e) { // O(n)
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
        System.out.println(set.contains(4)); // true
        set.remove(1);
        System.out.println(set); // LinkedListSet { data = 4 => 2 => null }
    }
}
