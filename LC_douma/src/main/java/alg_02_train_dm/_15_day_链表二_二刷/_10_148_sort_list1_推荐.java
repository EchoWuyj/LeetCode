package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:39
 * @Version 1.0
 */

public class _10_148_sort_list1_推荐 {

     /*
        148. 排序链表
        给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

        示例 1：
        输入：head = [4,2,1,3]
        输出：[1,2,3,4]

        示例 2：
        输入：head = [-1,5,3,4,0]
        输出：[-1,0,3,4,5]

        提示
        链表中节点的数目在范围 [0, 5 * 104] 内
        -105 <= Node.val <= 10^5
     */

    // KeyPoint 方法一 归并排序 => 递归实现(自顶朝下)
    public ListNode sortList1(ListNode head) {

        // KeyPoint 递归边界
        // 递归边界，不仅仅是链表为空 head == null
        // 当链表只有一个元素时，即 head.next == null，也是递归边界需要返回
        // 以后链表的特判，最好写上两个条件，比较保险
        if (head == null || head.next == null) return head;

        // KeyPoint 关键
        // 找到中间节点 => 归并排序中间节点需要：保证 '左中点'，不能是'右中点'
        // 所以这里 fast 先走一步，否则 slow 最后就是在'右中点'位置，不符合要求
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            // 每次走两步
            fast = fast.next.next;
        }

        // 记录右侧链表
        ListNode rightHead = slow.next;
        // 将中间以右断开，形成两个链表
        slow.next = null;

        // KeyPoint 1.递归过程
        // 对左边链表排序，返回排完序链表的表头
        ListNode left = sortList1(head);
        // 对右边链表排序，返回排完序链表的表头
        ListNode right = sortList1(rightHead);

        // KeyPoint 2.归的过程
        // 对左右两边排完序链表合并

        // KeyPoint 凡是涉及变量名，开头字母相同，特别容易混淆，自己要当心
        // rightHead 和 right 相混淆，导致代码出现 bug
        // => 最好避免，若是不能避免，则自己写代码时需要注意，下意识提高警惕
        return merge(left, right);
    }

    // 合并有序链表 => 返回合并后的头节点
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        // KeyPoint 使用 cur 串联剩余的链表，而不是直接 return l1 或者 l2
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummyNode.next;
    }
}


