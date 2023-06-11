package alg_02_train_dm._14_day_链表一;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 19:46
 * @Version 1.0
 */
public class _08_142_linked_list_cycle_ii {
    /*
       142. 环形链表 II
       给定一个链表，返回链表开始入环的第一个节点。
       如果链表无环，则返回 null。

       示例 1：
       输入：head = [3,2,0,-4], pos = 1
       输出：返回索引为 1 的链表节点
       解释：链表中有一个环，其尾部连接到第二个节点。

       提示：
       链表中节点的数目范围在范围 [0, 104] 内
       -105 <= Node.val <= 105
       pos 的值为 -1 或者链表中的一个有效索引

    */

    // KeyPoint 方法一 哈希查找
    public ListNode detectCycle1(ListNode head) {
        // visited 变量名，记住这种形式
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) return head;
            visited.add(head);
            head = head.next;
        }
        // 没有环，返回 null
        return null;
    }

    // KeyPoint 方法二 快慢指针
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode p = head;
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }
}
