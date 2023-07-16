package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:37
 * @Version 1.0
 */

public class _07_21_merge_two_sorted_lists1 {

     /*
        21. 合并两个有序链表
        将两个升序链表合并为一个新的 升序 链表并返回。
        新链表是通过拼接给定的两个链表的所有节点组成的。

        示例 1：
        输入：l1 = [1,2,4], l2 = [1,3,4]
        输出：[1,1,2,3,4,4]

        提示：
        两个链表的节点数目范围是 [0, 50]
        -100 <= Node.val <= 100
        l1 和 l2 均按 非递减顺序 排列
     */

    // KeyPoint 方法一 迭代实现
    // 时间复杂度 O(m+n) => 最坏情况，访问两个链表的每个元素
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        // 经过上面两个 if list1 != null && list2 != null
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;
        // list1 和 list2 同时不为 null，执行 while 循环
        // 否则，单一个节点 和 null 是没法比较的
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                // KeyPoint 加深对 cur.next = list1 理解
                // 1.cur.next 指向 list1 原来节点，没有创建一个新的节点
                // 2.cur 在遍历 list1 和 list2 中，修改了其中的节点
                //   ... 2 ...
                //       ↑
                //      cur
                //   cur 指向 2 节点，修改 cur.next，即修改 2 节点的 next 指向
                // 3.凡是没有 cur.next = new ListNode(...)
                //   cur.next = 引用(节点) => 都是在调整指针
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode head1 = createLinkedList(new int[]{1, 2, 4});
        ListNode head2 = createLinkedList(new int[]{1, 3, 4});
        ListNode mergeList = mergeTwoLists1(head1, head2);
        System.out.println("mergeList"); // 1 1 2 3 4 4
        printLinkedList(mergeList);
        // KeyPoint 链表 head1 被修改了，不是原来的 1 → 2 → 4
        System.out.println("head1");
        printLinkedList(head1); // 1 1 2 3 4 4
        // KeyPoint 链表 head2 被修改了，不是原来的 1 → 3 → 4
        System.out.println("head2");
        printLinkedList(head2); // 1 2 3 4 4
    }

    private static ListNode createLinkedList(int[] values) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummyHead.next;
    }

    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
