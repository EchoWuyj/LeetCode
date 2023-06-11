package alg_01_ds_dm._01_line._03_stack;

import java.util.NoSuchElementException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 12:01
 * @Version 1.0
 */

// ArrayStack 实现 Stack 接口，其泛型保持一致
public class ArrayStack<E> implements Stack<E> {
    // 静态数组实现
    private E[] data;
    // 栈元素个数
    private int size;

    public ArrayStack(int capacity) {
        // E[] 强转
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
        // 因为静态数组长度固定不可变化，size == data.length 此时静态数组已经装不下了
        // size 和 索引不一样
        // [1 2 3] => size
        //  0 1 2  => 索引
        if (size == data.length) {
            throw new RuntimeException("push failed, Stack is full");
        }
        // KeyPoint 可以简化 size 操作，这样写比较清楚，代码太简洁就会很难理解
        data[size] = e;
        size++;
    }

    @Override
    public E pop() {
        // 判断是否为空
        if (isEmpty()) {
            throw new NoSuchElementException("pop failed, stack is empty");
        }
        E ret = data[size - 1];
        size--;
        return ret;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        // 根据 Stack 中有的元素个数进行遍历，而不是根据 Stack 的容量来遍历
        // i < size
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
