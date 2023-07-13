package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 14:37
 * @Version 1.0
 */
public class _04_19_RemoveNthNodeFromEndOfList2 {

    // KeyPoint 方法三 递归实现 => 加深对递归理解
    // KeyPoint 链表问题天然支持递归解决
    // 1.递归边界
    // 2.递的过程
    // 3.归的过程
    // 递归含义：用于删除链表中倒数第 n 个节点，并将链表头节点返回
    private int count;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return process(head, n);
    }

    public ListNode process(ListNode head, int n) {
        if (head == null) return null;
        // 递的过程，不断深入，直到递归边界
        // removeNthFromEnd 执行完，又开始新的一轮 removeNthFromEnd，而不是执行 curr++ 以及后面的代码
        head.next = process(head.next, n);

        // 归的过程，只有到了递归边界之后，回溯过程，才会执行该代码 count++
        // 而不是从上到下执行代码，即执行 removeNthFromEnd 完，就执行 count++ 以及后面的代码
        count++;

        // 若 cur == n，返回除 cur 外，往后的链表，从将 curr 节点删除
        // 若 cur != n，返回 head，即整个链表
        if (count == n) return head.next;
        return head;
    }

    // KeyPoint 错误代码
    // 注意：不能将 count++ 已经整合到递归函数形参中
    // 原代码 count++ 是在 归的过程，从 0 开始计算，表示倒数第 n 个
    // 修改后 count++ 是在 递的过程，从 0 开始计算，表示正数第 n 个
    public ListNode process1(ListNode head, int n, int count) {
        if (head == null) return null;
        head.next = process1(head.next, n, count++);
        if (count == n) return head.next;
        return head;
    }
}
