package alg_01_ds_dm._01_line._02_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-03-08 21:42
 * @Version 1.0
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(5);
        System.out.println(linkedList);  // 5 => null

        linkedList.addFirst(10);
        System.out.println(linkedList); // 10 => 5 => null

        linkedList.add(2, 34);
        System.out.println(linkedList); // 10 => 5 => 34 => null

        System.out.println(linkedList.get(1)); // 5

        linkedList.addFirst(50);
        System.out.println(linkedList); // 50 => 10 => 5 => 34 => null

        linkedList.remove(2);
        System.out.println(linkedList); // 50 => 10 => 34 => null

        linkedList.removeFirst();
        System.out.println(linkedList); // 10 => 34 => null
    }
}
