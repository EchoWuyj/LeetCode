package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 19:45
 * @Version 1.0
 */

//
public class _06_19_remove_nth_node_from_end_of_list1 {

     /*
        19. 删除链表的倒数第 N 个结点
        给你一个链表，删除链表的 倒数 第 n 个结点，并且返回链表的头结点。

        示例 1：
        输入：head = [1,2,3,4,5], n = 2
        输出：[1,2,3,5]

        提示：
        链表中结点的数目为 sz
        1 <= sz <= 30
        0 <= Node.val <= 100
        1 <= n <= sz

     */

    // KeyPoint 方法一 两次遍历
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) return null;
        // 删除链表的倒数第 N 个结点 => 可能是头节点 => 使用 dummy
        // 通过虚拟节点，将表头节点看成非表头节点，统一删除逻辑
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 1.计算链表长度(不包括 dummy)
        // 计算 len 没有将 dummyNode 算在内
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        // 2.找到倒数第 n 个节点的前一个节点
        // KeyPoint 删除节点
        // 1.删除节点一定找到其前一个节点
        // 2.prev一定是从 dummy 开始，删除节点可能是头节点
        ListNode prev = dummy;
        // i 从 0 开始，因为计算 len 过程，也是没有将 dummy 包括在内的
        for (int i = 0; i < len - n; i++) {
            prev = prev.next;
        }

        // 3.删除倒数第 n 个节点
        // 注意：这里不能再使用 cur，cur 经过一轮遍历，cur 已经在链表结尾位置
        // 使用 delNode 记录待删除节点
        ListNode delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        // 4.返回头节点
        return dummy.next;
    }

    // KeyPoint 方法二 快慢指针
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        // 删除节点，一般都是使用 dummyNode
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        //                         fast
        //                          ↓
        // 1  2  3     4  5  6  7  null ， n = 4
        //       ↑     ↑
        //     slow  delNode

        // 1.删除倒数 N 节点，得知道前面一个节点，该位置为 slow
        // 2.从 fast 在为 null 位置，从而往前倒推 slow 位置
        //   => fast 和 slow 之间间隔 n+1 个节点
        //   => 故 fast 得先走 n + 1 步
        // 3.因为可能删除头节点，故且 fast 和 slow 都是从 dummy 开始

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        // 一次遍历
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }

}
