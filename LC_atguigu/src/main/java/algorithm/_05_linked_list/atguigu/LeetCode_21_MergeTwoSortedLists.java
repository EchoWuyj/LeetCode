package algorithm._05_linked_list.atguigu;

/**
 * @Author Wuyj
 * @DateTime 2022-03-16 16:10
 * @Version 1.0
 */
public class LeetCode_21_MergeTwoSortedLists {

    // 方法一:迭代法
    public ListNode mergeTwoLists01(ListNode l1, ListNode l2) {
        // 首先,定义一个哨兵节点,它的next指向最终结果的头节点
        // 方便后面流程的处理,即头节点不需要单独判断了
        ListNode sentinel = new ListNode(-1);

        // 需要保存当前结果链表里的最后一个节点,作为判断比较的“前一个节点”
        //  更改“前一个节点”的next指向当前更小的头节点
        ListNode prev = sentinel;

        // 迭代遍历两个链表,直到至少有一个为null
        while (l1 != null && l2 != null) {
            // 比较当前两个链表的头节点,较小的那个就接在结果链表末尾
            if (l1.val <= l2.val) {
                // 将l1头节点连接到prev后面
                prev.next = l1;
                // KeyPoint prev为指向当前节点的指针,而prev.next为当指向后面节点的next指针
                // 指针向前移动,以下一个节点作为链表头节点
                prev = l1;// prev指向的是结果链表的最后一个节点
                l1 = l1.next;
            } else {
                // 将l2头节点连接到prev后面
                prev.next = l2;
                // 指针向前移动,以下一个节点作为链表头节点
                prev = l2;
                l2 = l2.next;
            }
        }
        // 循环结束,最多还有一个链表没有遍历完成,因为已经排序,可以直接把剩余节点接到结果链表上
        prev.next = (l1 == null) ? l2 : l1;
        return sentinel.next;
    }

    // 方法二:递归
    public ListNode mergeTwoLists02(ListNode l1, ListNode l2) {
        // 递归的边界
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 比较头节点
        if (l1.val <= l2.val) {
            // l1头节点较小,直接提取出来,剩下的l1和l2继续合并,接在l1后面
            // KeyPoint 在该步骤可以认为后面的所有节点都已经处理完成,都已经合并成了完整的链表
            //  现在需要做的就是将l1接在链表的前面
            l1.next = mergeTwoLists02(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists02(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(4);

        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);

        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = null;

        listNode21.next = listNode22;
        listNode22.next = listNode23;
        listNode23.next = null;

        TestLinkedList.printList(listNode11);
        TestLinkedList.printList(listNode21);

        LeetCode_21_MergeTwoSortedLists mergeTwoSortedLists = new LeetCode_21_MergeTwoSortedLists();
        TestLinkedList.printList(mergeTwoSortedLists.mergeTwoLists01(listNode11, listNode21));
    }
}
