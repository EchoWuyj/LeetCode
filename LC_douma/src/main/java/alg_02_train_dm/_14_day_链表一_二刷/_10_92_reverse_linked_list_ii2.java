package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 18:42
 * @Version 1.0
 */
public class _10_92_reverse_linked_list_ii2 {

    // KeyPoint 方法二 头插法
    // 反转部分的链表满足题目遍历 1 次的要求
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为有头节点也可能变化，所以为了统一逻辑，这里设置虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 待删除节点的 pre，一定是从 dummy 开始，否则就没有起到 dummy 的作用
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 测试用例 [1,2,3,4,5] left = 2，right = 4

        //           left    right
        //            ↓       ↓
        //  -1 →  1 → 2 → 3 → 4 → 5 → null
        //   ↑    ↑   ↑   ↑
        // dummy pre cur next

        // 将 cur 和 next 交换位置 => 即 3 和 2 进行交换
        //  -1 →  1 → 3 → 2 → 4 → 5 → null
        //   ↑    ↑       ↑   ↑
        // dummy pre     cur next

        // 记录当前节点，对应 leftNode
        ListNode cur = pre.next;
        // left 后面还有 right - left 节点数，每个节点都要往前插入，即头插法
        // 故循环 right - left 次
        for (int i = 0; i < right - left; i++) {
            // cur 一直没有变化，for 循环不断将 cur 的 next 前移
            // pre 指针也没有变化，为了串联前移的 next 节点
            ListNode next = cur.next;
            // 头插法 => 类似于：逆时针调整指针
            cur.next = next.next;
            // next 节点 next 指针，链接到 pre 后面一个节点，即 pre.next
            next.next = pre.next;
            // 重新链接 pre 指针
            pre.next = next;
        }
        return dummy.next;

        // KeyPoint 刷力扣提交代码
        // 以后刷力扣，先执行代码，没有错误，再去提交，而不是将提交当做执行代码
    }
}
