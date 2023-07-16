package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 15:03
 * @Version 1.0
 */
public class _09_206_reverse_linked_list2 {

    // KeyPoint 自己写的递归代码 => 存在 bug
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rest = reverseList1(head.next);
        head.next = null;
        rest.next = head;
        // rest.next = head
        // rest 作为头节点，其 next 不能修改，否则原来整体返回后链表，只剩下两个元素
        // 即每次递归，head 都会覆盖第二个节点，
        // 5 → 4
        // 5 → 3
        // ...
        // 最后输出 5 → 1
        return rest;
    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rest = reverseList2(head.next);
        // 断链，避免形成环路
        head.next = null;
        // 关键：不使用 res，又想达成 rest.next = head 的效果
        // head.next.next = rest
        // 同时需要调整：head.next 和 head.next.next = head 位置
        // 最终代码见下面
        rest.next = head;
        return rest;
    }

    //  KeyPoint 方法二 递归
    // 链表具有天然递归性质
    // 将链表看成两部分：head 节点和除 head 外剩余 rest 链表
    // 1.head 只有一个节点，并不需要反转
    // 2.rest 链表，反转操作
    // 递归含义：返回反转后链表的头节点
    public ListNode reverseList(ListNode head) {

        // KeyPoint if 判断逻辑递进
        // if (head.next == null || head == null) ×
        // 两个条件顺序不能调换，若 head == null，此时 head.next 已经空指针异常了
        // 总结：底层判断条件放在前面，衍生判断条件放在后面

        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // 1.在递归调用 reverseList 之前的代码 => 递的过程
        // 递的过程
        // => 不断将大问题拆解成子问题过程
        // => 链表 => head + rest链表

        // 2.递归调用
        // head.next => rest 部分
        ListNode tail = reverseList(head.next);

        // 3.在递归调用 reverseList 之后的代码
        // => 归的过程
        // => 完成反转操作

        // rest.next = head
        head.next.next = head;
        head.next = null;

        // 递归最后一个节点，返回 tail，故 tail 表示链表最后一个节点
        return tail;
    }
}
