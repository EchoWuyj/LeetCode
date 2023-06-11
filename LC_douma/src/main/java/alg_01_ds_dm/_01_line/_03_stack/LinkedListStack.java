package alg_01_ds_dm._01_line._03_stack;

import alg_01_ds_dm._01_line._02_linkedlist.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 21:45
 * @Version 1.0
 */

public class LinkedListStack<E> implements Stack<E> {
    // 链表实现栈
    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack：[");
        // 打印输出是从栈底到栈顶的顺序，而链表表头作为栈顶，
        // 所以需要逆序打印，才是元素依次进栈顺序
        for (int i = linkedList.getSize() - 1; i >= 0; i--) {
            res.append(linkedList.get(i));
            if (i != 0) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
