package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 10:57
 * @Version 1.0
 */
public class _11_61_rotate_list1 {
     /*
        61. 旋转链表
        给你一个链表的头节点 head ，旋转链表，将链表每个节点向'右移动' k 个位置。

        输入：head = 1->2->3->4->5->NULL, k = 2
        输出：4->5->1->2->3->NULL
        => 类似于：旋转数组

        提示：
        链表中节点的数目在范围 [0, 500] 内
        -100 <= Node.val <= 100
        0 <= k <= 2 * 10^9
     */

    // KeyPoint 方法一 三次反转(数组思路)
    // 新题 => 旧题
    // 时间复杂度 O(n)
    public ListNode rotateRight1(ListNode head, int k) {

        // KeyPoint 说是循环旋转，但其实本质上：将尾部向前数，第 K 个元素作为头，原来的头接到原来的尾上
        // 原始链表  1 -> 2 -> 3 -> 4 -> 5  k = 2
        // 整体反转  5 -> 4 -> 3 -> 2 -> 1 => 反转之后，原链表尾变头，再去通过 k 来划分
        // 前 k 反转 4 -> 5 | 3 -> 2 -> 1
        // rest反转  4 -> 5 | 1 -> 2 -> 3
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
        ListNode reverseListHead = reverse(head);
        ListNode kthNode = reverseListHead;
        // i 从 0 开始，移动 k-1 步
        for (int i = 0; i < k - 1; i++) {
            kthNode = kthNode.next;
        }

        // 记录 k 节点的 next 节点，即后一部分链表的头节点
        ListNode restList = kthNode.next;
        // 断链
        kthNode.next = null;

        // 2.反转前一部分
        // KeyPoint reverse 传入 reverseListHead 而不是 kthNode
        ListNode returnHead = reverse(reverseListHead);

        // 测试用例
        // 输入
        // [1,2,3,4,5]
        // 2
        // 输出
        // [4]
        // 预期结果
        // [4,5,1,2,3]

        // KeyPoint 根据测试用例分析 bug
        // 一般输出节点个数比预期结果要少，多半是指针操作有问题
        // 1.断链
        // 2.定义的指针，是否发生变化
        // 3.传入指针是否正确

        // 3.反转后一部分，并且将前后链表串联
        // 前一半链表的头节点 reverseListHead 在反转之后，变成尾节点，可以用来串联
        reverseListHead.next = reverse(restList);
        return returnHead;
    }

    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 有返回值
        return pre;
    }
}
