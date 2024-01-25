package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 22:06
 * @Version 1.0
 */
public class _40_82_deleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {

        // 删除排序链表中的重复元素 II
        // 注意：删除原始链表中所有重复数字的节点，只留下不同的数字。

        // 判空
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 通过 cur 和 pre 来比较判断两元素是否重复
        ListNode cur = head;
        ListNode pre = dummy;

        // 使用 cur 遍历整个链表
        while (cur != null) {
            // KeyPoint 整体是一个 if else 语句，先做一层判断
            // 1.cur.next != null 保证后面 cur.next.val 不会出现空指针异常
            // 2.若元素出现相等，cur 指针一直向后遍历，cur一直移动到最后一个相同的元素
            //   此时不满足 while 循环条件，跳出 while 循环
            if (cur.next != null && cur.val == cur.next.val) {
                do {
                    cur = cur.next;
                } while (cur.next != null && cur.val == cur.next.val);

                // 调整 pre.next 指针
                pre.next = cur.next;
                // 断链，从而将重复元素全部删除
                cur.next = null;
                // 移动(更新) cur 指针位置
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
