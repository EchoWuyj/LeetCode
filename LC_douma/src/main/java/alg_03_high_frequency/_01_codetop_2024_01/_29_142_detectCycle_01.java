package alg_03_high_frequency._01_codetop_2024_01;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 18:58
 * @Version 1.0
 */
public class _29_142_detectCycle_01 {
    public ListNode detectCycle(ListNode head) {

        // 使用 Set 集合
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            // 判断是否包含
            if (set.contains(head)) return head;
            // 添加元素
            set.add(head);
            // head 向后移动
            head = head.next;
        }
        return null;
    }
}
