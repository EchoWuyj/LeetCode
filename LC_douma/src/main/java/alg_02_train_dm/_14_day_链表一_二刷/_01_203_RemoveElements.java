package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 10:09
 * @Version 1.0
 */

public class _01_203_RemoveElements {

     /*
        203. 移除链表元素
        给你一个链表的头节点 head 和一个整数 val ，
        请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。

        示例 1：
        输入：head = [1,2,6,3,4,5,6], val = 6
        输出：[1,2,3,4,5]

        提示：
        列表中的节点数目在范围 [0, 10^4] 内
        1 <= Node.val <= 50
        0 <= val <= 50

     */

    // KeyPoint 方法一 不使用虚拟节点
    //  => 区别表头节点和非表头节点
    public ListNode removeElements1(ListNode head, int val) {
        // 1.删除表头节点
        // KeyPoint 凡是指针引用，方法调用，必定保证不为 null
        // 后续和 head 相关操作，必须保证 head != null，否则空指针异常
        while (head != null && head.val == val) {
            // 标记删除节点，为了后面清除 delNode 的 next 指针
            // 因为那时 head 指针位置已经发生了变化
            ListNode delNode = head;
            head = head.next;
            // 最后清除指针，避免断链
            delNode.next = null;
        }
        // head == null，此时链表已为空
        // KeyPoint 加强判空意识
        // 调用 head.next 之前，进行判断，若 head 为 null，则空指针异常
        if (head == null) return null;

        // 2.删除非表头节点
        // 经过上面代码判断后，cur 从第二个节点开始判断
        ListNode cur = head.next;
        // 删除一个节点，需要 pre
        ListNode pre = head;
        // cur == null 循环结束
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur.next = null;
                // 移动 cur 指针
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
            // 可以将 cur 前移代码，从两个分支中抽取，但是这样就不直观了，不推荐
//            cur = pre.next;
        }
        return head;
    }

    // KeyPoint 方法二 虚拟节点 => 推荐
    //  => 统一删除头节点和非头节点的逻辑
    public ListNode removeElements(ListNode head, int val) {
        // 表头没有前一个节点 => 创建一个虚拟节点 => 统一操作
        ListNode dummy = new ListNode(-1);
        // 串联 dummy 和 head
        dummy.next = head;
        // cur 从 head 开始，不是从 dummy 开始
        ListNode cur = head;
        // 删除节点需要 pre 指针
        ListNode pre = dummy;
        while (cur != null) {
            // 遇到需要删除的节点
            if (cur.val == val) {
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
        // KeyPoint 调试代码
        // 部分测试用例通过，利用没有通过测试用例，调试代码
    }
}
