package alg_01_ds_dm._01_line._03_stack;

import alg_01_ds_dm._01_line._01_array.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 12:07
 * @Version 1.0
 */
public class DynamicArrayStack<E> implements Stack<E> {

    // 动态数组
    private ArrayList<E> data;

    // DynamicArrayStack 类的构造，不是 ArrayList 的构造
    public DynamicArrayStack(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        // ArrayList 中已经做过校验，故这里就不做校验
        data.addLast(e);
    }

    @Override
    public E pop() {
        // removeLast 方法中就存在返回删除元素值
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < data.getSize(); i++) {
            sb.append(data.get(i));
            if (i != data.getSize() - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
