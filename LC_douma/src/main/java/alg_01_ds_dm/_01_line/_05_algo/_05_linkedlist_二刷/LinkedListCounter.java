package alg_01_ds_dm._01_line._05_algo._05_linkedlist_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 21:06
 * @Version 1.0
 */
public class LinkedListCounter {

    // 计算一个链表中包含多少个节点数
    // KeyPoint 方式一 while 循环
    public int count(ListNode head) {
        // KeyPoint 养成特判习惯，为了程序的严谨性，不单单是为了通过测试用例 => "凡是指涉必有前提"
        if (head == null) return 0;
        int cnt = 0;
        ListNode curr = head;
        // 使用 while 循环，遍历整个链表
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }

    // KeyPoint 方式二 for 循环
    public int countFor(ListNode head) {
        if (head == null) return 0;
        int cnt = 0;
        // 使用 for 循环，遍历整个链表
        for (ListNode curr = head; curr != null; curr = curr.next) {
            cnt++;
        }
        return cnt;
    }

    // 计算链表等于目标值的节点个数
    public int countTarget(ListNode head, int target) {
        if (head == null) return 0;
        int cnt = 0;
        ListNode curr = head;
        // 使用 while 循环，遍历整个链表
        while (curr != null) {
            if (curr.val == target) cnt++;
            curr = curr.next;
        }
        return cnt;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{23, 12, 11, 34, 12});
        int cnt1 = new LinkedListCounter().count(head);
        System.out.println(cnt1);  // 5

        int cnt2 = new LinkedListCounter().countTarget(head, 12);
        System.out.println(cnt2); // 2
    }
}
