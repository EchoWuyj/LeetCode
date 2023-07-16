package alg_02_train_dm._15_day_链表二_二刷;

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
        给你两个单链表的头节点 headA 和 headB
        请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。

        题目数据 保证 整个链式结构中不存在环。
        注意，函数返回结果后，链表必须 保持其原始结构
        KeyPoint => 不能分隔链表，不能反转链表，不能动链表中其中一个节点

        进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？

        示例 1：
        输入：intersectVal = 2,
        listA = [9,8,2,5,4],listB = [1,4,3,2,5,4]
        skipA = 2, skipB = 3
        输出：Intersected at '8'

        解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
        从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
        在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

             headA
               ↓
               9 → 8↘
                      2 → 5 → 4 → null
          1 → 4 → 3↗
          ↑
          headB

        提示：
        listA 中节点数目为 m
        listB 中节点数目为 n
        1 <= m, n <= 3 * 104
        1 <= Node.val <= 105
        0 <= skipA <= m
        0 <= skipB <= n
        如果 listA 和 listB 没有交点，intersectVal 为 0
        如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]

     */

    // KeyPoint 方法一 哈希表
    // 思路：和判断连中是否有环(找环的入口)基本一致
    // 1.先将一个链表的所有节点都加入到 set 中
    // 2.再去遍历第二链表，找到第一个链表中节点
    // 时间复杂度 O(m+n)
    // 空间复杂度 O(m)
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
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
    // 一般很难想到，理解这种解法，并记住即可
    // 时间复杂度 O(m+n)
    // 空间复杂度 O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        // a 走完一轮 A 链表，再走 B 链表
        // b 走完一轮 B 链表，再走 A 链表
        // 最终 a 和 b 会相遇
        // 若相遇节点不为空，则说明有交点
        // 若为空，则说明没有交点
        while (a != b) {
            // 指针 a 下个节点的选择
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
        // 若 headA 和 headB 不相交，最终 a 和 b 都是 null
        // 跳出 while 循环，返回为 null
    }
    // KeyPoint 面试技巧
    // 1.面试中写算法过程，就是和老汤讲题的过程一样
    //   不要上来就是最优解，而是使用最常想到的方法，面试官可能不满意
    // 2.于是在面试官的提示下(要求下)，后续不断优化，从而到最优解
    //   关键：懂得包装，不能能让人感觉你是背代码，而是通过'有理有据'想出来
    //   能够分析代码，对代码中存在的问题，进行优化解决，从而提高代码性能
    //   这才是面试官需要的
}
