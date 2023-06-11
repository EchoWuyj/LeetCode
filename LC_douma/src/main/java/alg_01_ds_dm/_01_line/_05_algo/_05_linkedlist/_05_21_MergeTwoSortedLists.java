package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 12:56
 * @Version 1.0
 */
public class _05_21_MergeTwoSortedLists {

    /*
        21. 合并两个有序链表
        将两个升序链表合并为一个新的 升序 链表并返回。
        新链表是通过拼接给定的两个链表的所有节点组成的。

        示例 1：
        输入：l1 = [1,2,4], l2 = [1,3,4]
        输出：[1,1,2,3,4,4]

     */

    // KeyPoint 方法一 迭代实现
    // 时间复杂度 O(m+n) => 最坏情况，访问两个链表的每个元素
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // 经过上面两个 if l1 != null && l2 != null

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

        // l1 和 l2 同时不为 null，执行 while 循环
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;

        return dummyNode.next;
    }

    // KeyPoint 方法二 递归实现
    // 满足递归 3 条件
    // 1.大问题 => 拆分小问题
    // 2.小问题求解和大问题求解一样
    // 3.存在最小子问题
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 递归边界
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 根据 val 值大小，拆分子问题
        if (l1.val <= l2.val) {
            // 递的过程 => 不断拆解子问题 => l1.next 和 l2 组成新的两个链表
            // mergeTwoLists 返回值 => 两个链表合并之后的头节点
            // l1 的 next 指针指向：两个链表合并之后的头节点
            l1.next = mergeTwoLists(l1.next, l2);
            // 归的过程 => 不断将链表表头返回上层
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
