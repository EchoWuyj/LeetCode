package alg_03_high_frequency._01_codetop_2024_01_Top100;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 12:45
 * @Version 1.0
 */
public class _16_141_hasCycle_01 {

    // 环形链表
    // HashSet
    public boolean hasCycle(ListNode head) {
        // 特判
        if (head == null || head.next == null) return false;
        // 存储 ListNode 的 set 集合
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            // add 之前先判断
            if (set.contains(head)) return true;
            set.add(head);
            // head 移动到下个位置
            head = head.next;
        }
        // 循环结束都没有找到环，返回 false
        return false;
    }
}
