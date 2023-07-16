package alg_02_train_dm._14_day_链表一_二刷;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 19:46
 * @Version 1.0
 */
public class _07_141_linked_list_cycle {

    /*
        141. 环形链表
        给定一个链表，判断链表中是否有环。
        如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
        为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
        如果 pos 是 -1，则在该链表中没有环。
        注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
        如果链表中存在环，则返回 true 。 否则，返回 false 。


        3 → 2 → 0 → -4
            ↑ ← ← ← ←↑
        示例 1：
        输入：head = [3,2,0,-4], pos = 1
        输出：true
        解释：链表中有一个环，其尾部连接到第二个节点。

        提示：
        链表中节点的数目范围是 [0, 10^4]
        -10^5 <= Node.val <= 10^5
        pos 为 -1 或者链表中的一个 有效索引 。

        进阶：
        你能用 O(1)（即，常量）内存解决此问题吗？
     */

    // KeyPoint 方法一 哈希查找
    // 思路：哈希表用于记录链表的节点是否被访问过了
    //       若已经被访问过的节点又被访问了则说明有环
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            // KeyPoint Set 存对象数据类型和 integer 是不同的
            // ListNode 是对象数据类型，其中 set 集合中存储的不是 head.val，而是 node 对象引用(地址值)
            // 因为每个 node 对象的地址值都是唯一的，所以不存在重复，除非存在环
            if (set.contains(head)) return true;
            // KeyPoint 若 set 中不存在 head，则一定需要将其加入 set，否则遇到环形链表，则陷入死循环
            set.add(head);
            head = head.next;
        }
        return false;
    }

    // KeyPoint 方法二 快慢指针
    // 思路：当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。
    //       当链表中存在环时，每一轮移动后，快慢指针的距离将减小1，而初始距离为环的长度，因此至多移动 N 轮。
    // 时间复杂度：O(n):
    // 空间复杂度：O(1)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // KeyPoint 快慢指针，while 循环条件
        // 循环结束条件：fast == null || fast.next == null
        // => 最后 fast 指针要么在链表结尾节点，要么在结尾节点后一个为 null 位置
        // => 因为 fast 一次走两步，所以最后不确定 fast 结束位置，所以才有两种可能
        // 1.fast == null => 链表为偶数
        // 2.fast.next == null  => 链表为奇数
        // 相反逻辑 => 循环执行条件 fast != null && fast.next != null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
