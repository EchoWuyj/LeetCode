package alg_03_leetcode_top_zcy.class_01_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-13 11:33
 * @Version 1.0
 */

// 两数相加
public class problem_002_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {

        // 考察点：链表调整
        // 思路:按照链表方式,对应节点相加,得到链表再去逆序

        // 进位
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        // 相加的结果
        int n = 0;

        ListNode c1 = head1;
        ListNode c2 = head2;
        ListNode node = null;
        ListNode pre = null;

        // 只要有其中1个链表,遍历的节点位置不为null,while循环一直执行
        while (c1 != null || c2 != null) {
            n1 = c1 != null ? c1.val : 0;
            n2 = c2 != null ? c2.val : 0;
            // 中间的进位信息已经考虑了
            n = n1 + n2 + ca;
            // 进位信息
            ca = n / 10;
            // 前一个节点需要不断跟着node节点后移
            pre = node;
            node = new ListNode(n % 10);
            // 当前节点往前连接
            node.next = pre;

            // c1和c2是需要不断往链表后移的
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }

        // 最右侧的进位信息
        if (ca == 1) {
            pre = node;
            node = new ListNode(1);
            node.next = pre;
        }

        return reverseList(node);
    }

    // 反转链表
    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            // 先移动pre,再移动cur
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

