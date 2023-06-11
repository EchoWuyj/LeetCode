package alg_01_ds_dm._01_line._04_queue;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 18:57
 * @Version 1.0
 */
public class Test_System_Queue {
    public static void main(String[] args) {
        // KeyPoint 系统实现的双端队列
        System.out.println("==== arrayDeque ====");
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(3);
        System.out.println(arrayDeque);  // first [3, 2, 1] last => 左(first) | 右(last) => '从左往右'顺序
        arrayDeque.removeFirst();
        System.out.println(arrayDeque);  // [2, 1]
        arrayDeque.addLast(4);
        arrayDeque.addLast(5);
        arrayDeque.addLast(6);
        System.out.println(arrayDeque); // first [2, 1, 4, 5, 6] last
        System.out.println(arrayDeque.removeLast()); // 有返回值 6
        System.out.println(arrayDeque); // [2, 1, 4, 5]

        System.out.println("==== linkedList ===="); // 打印输出，同上
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        System.out.println(linkedList);
        System.out.println(linkedList.removeLast());
        System.out.println(linkedList);
    }
}
