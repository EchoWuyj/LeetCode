package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:11
 * @Version 1.0
 */
public class _22_92_reverseBetween {

    // 反转链表 II
    // 直接模拟 + 头插法
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // 头节点可能发生变化
        ListNode dummy = new ListNode(-1);
        // 串联 head 节点
        dummy.next = head;
        // 定义指针
        ListNode pre = dummy;

        // KeyPoint 注意
        // for 循环，i=0，i<n，则循环 n 次
        // => i=0,i<left-1，则循环 left-1 次

        // pre 移动 left 前一个位置
        // 如 left 为 3，表示第 3 位置(从 1 开始计数)，pre 则移动第 2 位置
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 只有先确定 pre 位置，才能确定 cur 位置，标记 cur
        ListNode cur = pre.next;

        // pre cur
        //  ↓   ↓
        //  _   _   _   _
        //      ↑       ↑
        //    left    right
        // 反转 [left,right]，只需要交换 right - left 次即可

        // 头插法 => 逆时针调整
        for (int i = 0; i < right - left; i++) {

            // KeyPoint 整体结构首尾相接
            // next -> cur.next -> next.next -> pre.next -> next

            // cur 节点位置没有发生变化
            // 先确定 next 节点位置
            ListNode next = cur.next;
            // 移动 cur 节点的 next 指针
            cur.next = next.next;
            // 移动 next 节点的 next 指针
            next.next = pre.next;
            // 移动 pre 节点的 next 指针
            pre.next = next;
        }
        return dummy.next;
    }
}
