package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 21:12
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
    // 区别：表头节点和非表头节点
    public ListNode removeElements1(ListNode head, int val) {
        // 1.删除表头节点
        // KeyPoint 后续和 head 相关操作，必须保证 head != null，否则空指针异常
        while (head != null && head.val == val) {
            // 标记删除节点，为了后面清除 next 指针
            ListNode delNode = head;
            head = head.next;
            // 最后清除指针，避免断链
            delNode.next = null;
        }
        // head == null，此时链表已为空
        if (head == null) return null;

        // 2.删除非表头节点
        // 经过上面代码判断后，curr 从第二个节点开始判断
        ListNode curr = head.next;
        // 删除一个节点，需要 prev
        ListNode prev = head;
        // curr == null 循环结束
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
            // 可以将 curr 前移代码，从两个分支中抽取，但是这样就不直观了
//            curr = prev.next;
        }
        return head;
    }

    // KeyPoint 方法二 虚拟节点 => 推荐
    // 使用虚拟节点统一删除头节点和非头节点的逻辑
    public ListNode removeElements(ListNode head, int val) {
        // 表头没有前一个节点 => 创建一个虚拟节点 => 统一操作
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        // curr 从 head 开始，不是从 dummyNode 开始
        ListNode curr = head;
        ListNode prev = dummyNode;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = prev.next;
        }
        return dummyNode.next;
    }
}
