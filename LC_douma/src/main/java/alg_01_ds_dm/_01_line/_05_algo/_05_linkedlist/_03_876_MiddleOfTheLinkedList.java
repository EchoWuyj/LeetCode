package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 11:49
 * @Version 1.0
 */
public class _03_876_MiddleOfTheLinkedList {


    /*
        876. 链表的中间结点
        给你单链表的头结点 head ，请你找出并返回链表的中间结点(中间结点)。
        如果有两个中间结点，则返回第二个中间结点。

        示例 1：
        输入：head = [1,2,3,4,5]
        输出：[3,4,5]
        解释：链表只有一个中间结点，值为 3
        KeyPoint 输出的中间结点依然存在链表关系，所以输出为链表形式，而不是单个节点；
                 若要输出单是中间结点值，则 middleNode.val;

        示例 2：
        输入：head = [1,2,3,4,5,6]
        输出：[4,5,6]
        解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。

     */

    // KeyPoint 方法一 两次遍历
    public static ListNode middleNode1(ListNode head) {

        if (head == null || head.next == null) return head;
        // 1. 计算链表的长度
        int length = 0;
        ListNode curr = head;
        while (curr != null) { // O(n)
            length++;
            curr = curr.next;
        }

        // 2. 找到链表的中间节点
        for (int i = 0; i < length / 2; i++) { // O(n/2)
            head = head.next;
        }

        // KeyPoint 关于 n/2 说明，关键：区分'中点位置索引'和'到中点位置需要走的步数'
        // 1 2 3 4 5 => (5/2)+1=3
        // 1 2 3 4 5 6 => (6/2)+1=4
        // 对奇偶通用，计算中点值的公式：(n/2)+1
        // 但从 1 到 3，只需要走 2 步即可，故循环次数 n/2

        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(middleNode1(head)); // 3->4->5->null
    }

    // KeyPoint 方法二 快慢指针
    // 该算法非常重要，该算法是其他链表算法基础
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        // 一次遍历
        // 循环结束条件 fast == null || fast.next == null
        // 相反 => 循环执行条件 fast != null && fast.next != null
        // fast 和 fast.next 分别对应'奇偶'情况
        while (fast != null && fast.next != null) {
            // 慢指针每次走一步
            slow = slow.next;
            // 快指针每次走两步
            fast = fast.next.next;
        }
        return slow;
    }
}

