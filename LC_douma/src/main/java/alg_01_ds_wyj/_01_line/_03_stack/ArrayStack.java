package alg_01_ds_wyj._01_line._03_stack;

import java.util.NoSuchElementException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 13:48
 * @Version 1.0
 */
public class ArrayStack<E> implements Stack<E> {

    private E[] data;
    private int size;

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(E e) {
        if (size == data.length) {
            throw new RuntimeException("push failed, Stack is full");
        }
        data[size] = e;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("peek failed, stack is empty");
        }
        E res = data[size - 1];
        size--;
        return res;
    }

    @Override
    public E peek() {
        // 判断栈是否为空
        if (isEmpty()) {
            throw new NoSuchElementException("peek failed, stack is empty");
        }
        return data[size - 1];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack：[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("] top");
        return builder.toString();
    }
}
