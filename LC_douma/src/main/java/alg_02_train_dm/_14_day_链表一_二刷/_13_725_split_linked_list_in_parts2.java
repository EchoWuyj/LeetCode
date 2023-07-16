package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 23:40
 * @Version 1.0
 */
public class _13_725_split_linked_list_in_parts2 {

    // KeyPoint 方法二 调整链表指针
    // 基于原始链表，调整指针实现分隔，不用拷贝节点，节省空间
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int width = len / k;
        int remainder = len % k;
        cur = head;
        ListNode[] res = new ListNode[k];
        for (int i = 0; i < k; i++) {

            // k =2
            // head
            //  ↓
            //  1 → 2 → 3 → 4 | → 5 → 6 → 7 → null
            //  ↑
            // cur
            //  ↑
            // newHead

            // k =2
            // head
            //  ↓
            //  1 → 2 → 3 → 4 |   5 → 6 → 7 → null
            //              ↑     ↑
            //             cur   next
            //  ↑
            // newHead

            // k =2
            // head
            //  ↓
            //  1 → 2 → 3 → 4 |   5 → 6 → 7 → null
            //                    ↑
            //                   cur
            //                    ↑
            //                  newHead

            ListNode newHead = cur;
            // 不使用 dummy 后，每次都从 newHead 开始，故走的步数需要减 1
            int realWidth = width + (remainder > i ? 1 : 0) - 1;

            // KeyPoint 解释为什么需要 -1
            // 每一段 cur 需要走的步数，比这一段的节点数少 1 个
            // 如：1 -> 2 -> 3 -> 4
            // 链表的长度为 4 ，也就是 4 个节点，但是从第一个节点开始
            // 只需要走 3 步就可以到达最后一个节点

            for (int j = 0; j < realWidth; j++) {
                // KeyPoint 防御式编程
                // 必须加上非空判断，因为遍历链表长度可能不够
                // 若链表很短，则 cur.next 必然空指针异常
                if (cur != null) cur = cur.next;
            }
            // 跳出 for 循环，cur 在一组分隔链表的最后位置
            // 此时，若 cur.next 不为 null，则说明还有下一组
            // 但是调用 cur.next 之前，先判断 cur 是否为 null，否则空指针异常
            if (cur != null) {
                ListNode next = cur.next;
                // 断链
                cur.next = null;
                // cur 到下一段分隔链表的表头
                cur = next;
            }
            res[i] = newHead;
        }
        return res;
    }
}
