package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 21:36
 * @Version 1.0
 */
public class _02_206_ReverseLinkedList {

    /*
        206. 反转链表
        给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

        示例 1
        输入：head = [1,2,3,4,5]
        输出：[5,4,3,2,1]

        提示：
        链表中节点的数目范围是 [0, 5000]
        -5000 <= Node.val <= 5000
     */

    // KeyPoint 方法一 迭代法
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            // 记录 curr 后一个节点，避免移动 cur.next 指针，导致找不到后面一个节点
            // 即：移动指针之前，先将后面一个节点信息，保存在 next 中，方便后续使用
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //  KeyPoint 方法二 递归
    // 链表具有天然递归性质，head 节点和除 head 外剩余 rest 链表
    // 1.head 只有一个节点，并不需要反转
    // 2.rest 链表，反转操作
    public ListNode reverseList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // KeyPoint 错误形式 => 两个条件顺序不能调换
        // 若 head == null，此时 head.next 已经空指针异常了
        // 底层判断条件放在前面，衍生判断条件放在后面
        // if (head.next == null || head == null)

        // 1.在递归调用 reverseList 之前的代码 => 递的过程
        // 递的过程 => 不断将大问题拆解成子问题过程：链表 => head + rest链表

        // 2.递归调用
        // head.next => rest 部分
        ListNode tail = reverseList(head.next);

        // 3.在递归调用 reverseList 之后的代码 => 归的过程 => 完成反转操作
        // rest.next = head
        head.next.next = head;
        head.next = null;

        // 递归最后一个节点，返回 tail，故 tail 表示链表最后一个节点
        return tail;
    }
}
