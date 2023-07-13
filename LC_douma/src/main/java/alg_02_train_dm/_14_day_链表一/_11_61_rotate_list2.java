package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 20:04
 * @Version 1.0
 */
public class _11_61_rotate_list2 {

    // KeyPoint 方法二 调整指针(链表思路)
    public ListNode rotateRight2(ListNode head, int k) {

        // 链表通过指针调整很灵活，只要找到'关键节点'即可
        // 关键节点：head，lastNode，newTail，newHead

        //         head               lastNode
        //           ↓                   ↓
        // 原始链表  1 -> 2 -> 3 -> 4 -> 5  k = 2
        //                    ↑     ↑
        //                newTail newHead

        if (head == null || head.next == null || k == 0) return head;
        int len = 0;
        ListNode lastNode = head;
        // 确定 lastNode 位置
        // 1.在 while 循环条件中，使用 lastNode.next，保证 lastNode 为最后一个节点
        // 2.因为 while 最后判断的是 lastNode.next，而 lastNode 本身没有发生变化
        while (lastNode.next != null) {
            len++;
            lastNode = lastNode.next;
        }

        // KeyPoint  说明：len++
        // while 循环条件，lastNode 为链表最后一个位置，lastNode.next == null
        // 故循环体不执行 len++，故 len 少计算了一个，故还得 len++
        len++;

        if (k % len == 0) return head;
        k = k % len;

        ListNode newTail = head;

        // KeyPoint 前后次序转换
        // 倒数第 k => 转成 正数第 len-k+1 个
        // 如：1 2 3 4 5
        //            ↑
        // 倒数第 1 => 正数第 5 => 5 = 5-1+1
        // 同时，因为涉及移动指针，newTail 只要到 len-k 个即可，移动 len-k-1 步
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        // 断链
        newTail.next = null;
        lastNode.next = head;

        return newHead;
    }
}
