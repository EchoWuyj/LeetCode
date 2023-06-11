package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 10:57
 * @Version 1.0
 */
public class _11_61_rotate_list {
     /*
        61. 旋转链表
        给你一个链表的头节点 head ，旋转链表，将链表每个节点向'右移动' k 个位置。

        输入：head = 1->2->3->4->5->NULL, k = 2
        输出：4->5->1->2->3->NULL
        => 类似于：旋转数组
        =>

        提示：
        链表中节点的数目在范围 [0, 500] 内
        -100 <= Node.val <= 100
        0 <= k <= 2 * 10^9
     */

    // KeyPoint 方法一 三次反转(数组思路)
    // 时间复杂度 O(n)
    public ListNode rotateRight1(ListNode head, int k) {

        // KeyPoint 说是循环旋转，但其实本质上：将尾部向前数，第 K 个元素作为头，原来的头接到原来的尾上
        // 原始链表  1 -> 2 -> 3 -> 4 -> 5  k = 2
        // 整体反转  5 -> 4 -> 3 -> 2 -> 1 => 反转之后，原链表尾变头，再去通过 k 来划分
        // 前 k 反转 4 -> 5 | 3 -> 2 -> 1
        // 后面反转  4 -> 5 | 1 -> 2 -> 3
        // 前后串联  4 -> 5 -> 1 -> 2 -> 3

        if (head == null || head.next == null || k == 0) return head;
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        // k 是数组长度的整数倍，则不需要旋转链表
        if (k % len == 0) return head;
        // 将 k 中存在整数倍的 len 去除掉
        // 即保证 k 一定是小于 len 的，在数组中，避免 [k] 越界
        k = k % len;

        // 1.反转整个链表
        ListNode newHead = reverse(head);
        ListNode kthNode = newHead;
        // i 从 0 开始
        for (int i = 0; i < k - 1; i++) {
            kthNode = kthNode.next;
        }

        // 记录 k 节点的 next 节点，即后一部分链表的头节点
        ListNode nextHead = kthNode.next;
        kthNode.next = null;

        // 2.反转前一部分
        ListNode retHead = reverse(newHead);

        // 3.反转后一部分，并且将前后链表串联
        // 前一半链表的头节点 newHead 在反转之后，变成尾节点，可以用来串联
        newHead.next = reverse(nextHead);

        return retHead;
    }

    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // 有返回值
        return prev;
    }

    // KeyPoint 方法二 调整指针(链表思路)
    // 链表通过指针调整很灵活，只要找到关键节点即可
    public ListNode rotateRight2(ListNode head, int k) {

        //         head               lastNode
        //           ↓                   ↓
        // 原始链表  1 -> 2 -> 3 -> 4 -> 5  k = 2
        //                    ↑     ↑
        //                newTail newTail

        if (head == null || head.next == null || k == 0) return head;
        // while 循环中，判断从 lastNode.next 开始，故 len = 1
        int len = 1;
        ListNode lastNode = head;
        // 使用 lastNode.next，保证 lastNode 为最后一个节点
        while (lastNode.next != null) {
            len++;
            lastNode = lastNode.next;
        }
        if (k % len == 0) return head;
        k = k % len;

        ListNode newTail = head;
        //
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        lastNode.next = head;

        return newHead;
    }
}
