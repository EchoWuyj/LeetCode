package alg_01_ds_dm._01_line._03_stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 11:57
 * @Version 1.0
 */
public interface Stack<E> {
    /**
     * 查看栈中元素个数
     *
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 进栈
     * 将元素 e 压入栈中
     *
     * @param e
     */
    void push(E e);

    /**
     * 出栈(砰地一声出来)
     * 将栈顶的元素出栈
     *
     * @return
     */
    E pop();

    /**
     * 查询栈顶的元素(瞟一眼)
     *
     * @return
     */
    E peek();
}
