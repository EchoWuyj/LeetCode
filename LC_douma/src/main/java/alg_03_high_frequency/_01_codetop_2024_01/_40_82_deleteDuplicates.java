package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 22:06
 * @Version 1.0
 */
public class _40_82_deleteDuplicates {

    // 删除排序链表中的重复元素 II
    // 删除原始链表中所有重复数字的节点，只留下不同的数字。
    // 直接模拟
    public ListNode deleteDuplicates(ListNode head) {

        // 判空
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 通过 pre 和 cur 指针，比较判断两元素是否重复
        ListNode pre = dummy;
        ListNode cur = head;

        // 使用 cur 遍历整个链表
        while (cur != null) {

            // KeyPoint 整体是通过 if else 语句先做一层判断

            // cur.next != null 保证后面 cur.next.val 不会出现空指针异常
            if (cur.next != null && cur.val == cur.next.val) {
                do {
                    cur = cur.next;
                    // 若元素出现相等，cur 指针一直向后遍历，cur 一直移动到最后一个相同的元素
                    // 此时不满足 while 循环条件，跳出 while 循环
                } while (cur.next != null && cur.val == cur.next.val);
                // 调整 pre.next 指针，指向删除重复数字后的不同的数字
                pre.next = cur.next;
                // 断链，从而将重复元素全部删除
                cur.next = null;
                // 移动 cur 指针，指向下个元素
                cur = pre.next;
            } else {
                // pre 和 cur 指针后移
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
