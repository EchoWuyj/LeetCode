package alg_02_train_dm._15_day_链表二;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:35
 * @Version 1.0
 */
public class _04_160_intersection_of_two_linked_lists {

      /*
        160. 相交链表
        给你两个单链表的头节点 headA 和 headB ，
        请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。

        题目数据 保证 整个链式结构中不存在环。
        注意，函数返回结果后，链表必须 保持其原始结构 。
        进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？

        示例 1：
        输入：intersectVal = 8,
        listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
        输出：Intersected at '8'

        解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
        从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
        在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

     */

    // KeyPoint 方法一 哈希表 => 思路和判断连中是否有环基本一致

    // 时间复杂度 O(m+n)
    // 空间复杂度 O(m)
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Set<ListNode> set = new HashSet<>();
        // 将所以 A 放入 set 中
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    // KeyPoint 方法二 双指针
    // 一般很难想到，理解这种解法，并记住
    // 时间复杂度 O(m+n)
    // 空间复杂度 O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;
        // a 走完一轮 A 链表，再走 B 链表
        // b 走完一轮 B 链表，再走 A 链表，最终 a 和 b 会相遇
        // 若相遇节点不为空，则说明有交点
        // 若为空，则说明没有交点
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }
    // KeyPoint 面试中写算法过程，就是和老汤讲题的过程一样
    //  => 不要上来就是最优解，而是使用最常想到的方法，面试官可能不满意
    //  => 于是在面试官的提示下(要求下)，后续不断优化，从而到最优解
    //  => 关键：懂得包装，不能能让人感觉你是背代码，而是通过'有理有据'想出来，
}
