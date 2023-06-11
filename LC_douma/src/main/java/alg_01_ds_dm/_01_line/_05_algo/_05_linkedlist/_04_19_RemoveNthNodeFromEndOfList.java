package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 12:07
 * @Version 1.0
 */
public class _04_19_RemoveNthNodeFromEndOfList {

    /*
        19. 删除链表的倒数第 N 个结点
        给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

        示例 1：
        输入：head = [1,2,3,4,5], n = 2
        输出：[1,2,3,5]

     */

    // KeyPoint 方法一 两次遍历
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) return null;
        // 通过虚拟节点，将表头节点看成非表头节点，统一删除逻辑
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        // 1. 计算链表长度
        int length = 0;
        ListNode curr = dummyNode;

        while (curr != null) {
            length++;
            curr = curr.next;
        }
        // 2. 找到倒数第 n 个节点的前一个节点
        // KeyPoint 删除节点
        // 1.删除节点一定找到其前一个节点
        // 2.前一个节点一定是从 dummyNode 开始
        ListNode prev = dummyNode;
        // i 从 1 开始，因为计算 length 过程，也是将 dummyNode 包括在内的
        for (int i = 1; i < length - n; i++) {
            prev = prev.next;
        }
        // 3. 删除倒数第 n 个节点
        // 这里不能再使用 cur，cur 经过一轮遍历，cur 已经在链表结尾位置
        // 使用 delNode 记录待删除节点
        ListNode delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = head;
        int count = 0;
        // 计算 count 没有将 dummyNode 算在内
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        ListNode prev = dummyNode;
        // i 从 0 开始
        for (int i = 0; i < count - n; i++) {
            prev = prev.next;
        }

        ListNode delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        return dummyNode.next;
    }

    // KeyPoint 方法二 快慢指针
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        if (head == null) return null;
        // 删除节点，一般都是使用 dummyNode
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        //                     fast
        //                      ↓
        // 1  2  3     4  5  6  7 ， n = 4
        //       ↑     ↑
        //     slow  delNode

        // 从 fast 在最后一个位置，从而往前倒推 slow 位置
        //  => fast 和 slow 之间间隔 n 个节点
        // 且 fast 和 slow 都是从 dummyNode 开始，加上该节点一步
        // => 故 fast 得先走 n + 1 步
        while (n >= 0) { //  n >= 0 不是 n <= 0
            fast = fast.next;
            n--;
        }

        // 一次遍历
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }

    // 通过全局变量 curr，
    static int curr = 0;

    // KeyPoint 方法三 递归实现
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        // 递的过程，不断深入，直到递归边界
        // removeNthFromEnd 执行完，又开始新的一轮 removeNthFromEnd
        // 而不是执行 curr++ 以及后面的代码
        head.next = removeNthFromEnd(head.next, n);

        // 归的过程，只有到了递归边界之后，回溯过程，才会执行该代码，
        // 而不是从上到下执行代码，即执行 removeNthFromEnd 完，就执行 curr++ 以及后面的代码
        curr++;

        // 若 cur == n，返回除 cur 外，往后的链表，从将 curr 节点删除
        // 若 cur != n，返回 head，即整个链表
        if (curr == n) return head.next;
        return head;
    }
}
