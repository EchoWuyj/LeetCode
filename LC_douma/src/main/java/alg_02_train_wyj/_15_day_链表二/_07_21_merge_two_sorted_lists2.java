package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 20:11
 * @Version 1.0
 */
public class _07_21_merge_two_sorted_lists2 {

    // KeyPoint 自己递归代码 => 存在 bug => 已经解决
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        return process(l1, l2);
    }

    public static ListNode process(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            // 1.不能将 cur 定义全局变量，否则，每次递归调用时，都会重新赋值给 cur 一个新的节点
            //   这会导致丢失之前的节点链接，无法正确地构建链表
            // 2.正确做法：每次都是创建一个新 cur，然后将头节点返回
            ListNode cur = new ListNode(l1.val);
            cur.next = process(l1.next, l2);
            return cur;
        } else {
            ListNode cur = new ListNode(l2.val);
            cur.next = process(l1, l2.next);
            return cur;
        }

        // cur 是 if 判断的局部变量，无法在 if 之外直接返回，只能在 if 里面返回
        // return cur;
    }

    public static void main(String[] args) {
        ListNode head1 = createLinkedList(new int[]{1, 3});
        ListNode head2 = createLinkedList(new int[]{2, 4});

        ListNode head = mergeTwoLists1(head1, head2);
        printLinkedList(head);
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
