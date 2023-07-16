package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 12:28
 * @Version 1.0
 */
public class _12_328_odd_even_linked_list {
       /*
        328. 奇偶链表
        给定一个单链表，把所有的 奇数节点 和 偶数节点 分别排在一起。
        请注意，这里的 奇数节点 和 偶数节点 指的是节点编号的奇偶性，而不是节点的值的奇偶性。

        请尝试使用原地算法完成。
        你的算法的空间复杂度应为 O(1)，
        时间复杂度应为 O(nodes)，nodes 为节点总数。

        应当保持奇数节点和偶数节点的相对顺序。
        链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推

        示例 1:
        输入: head = [1,2,3,4,5]
        输出: [1,3,5,2,4]

        示例 2:
        输入: head = [2,1,3,5,6,4,7]
        输出: [2,3,6,7,1,5,4]

        [2,1,3,5,6,4,7]
         1 2 3 4 5 6 7

        [2,3,6,7,1,5,4]
         1 3 5 7 2 4 6

        提示:
        n == 链表中的节点数
        0 <= n <= 104
        -106 <= Node.val <= 106
     */

    public ListNode oddEvenList(ListNode head) {
        // 链表个数 <= 2，直接返回 head
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // oddHead evenHead
        //    ↓      ↓
        //    1   →  2 → 3 → 4 → 5 → null
        //    ↑      ↑
        //   odd    even

        // odd 和 even 奇偶指针，遍历整个数组
        // odd 和 even 交错指向后面节点
        ListNode odd = head, even = head.next;
        // 用于后面串联
        ListNode evenHead = head.next;
        // 仅仅通过修改指针实现串联
        // 以链表靠后的节点为判空条件，even 比 odd 更加靠后，所以使用 even 节点判空
        while (even != null && even.next != null) {
            // 修改 odd 的 next 指针，even.next 为对应节点的下个节点
            odd.next = even.next;
            // odd 后移
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
