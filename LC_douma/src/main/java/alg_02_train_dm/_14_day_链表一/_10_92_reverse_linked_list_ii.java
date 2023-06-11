package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 20:39
 * @Version 1.0
 */
public class _10_92_reverse_linked_list_ii {
     /*
        92. 反转链表 II
        给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
        请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

        输入：head = 1->2->3->4->5, left = 2, right = 4
        输出： 1->4->3->2->5

        提示：
            链表中节点数目为 n
            1 <= n <= 500
            -500 <= Node.val <= 500
            1 <= left <= right <= n

        进阶： 你可以使用一趟扫描完成反转吗？
     */

    // KeyPoint 方法一 直接模拟
    public ListNode reverseBetween1(ListNode head, int left, int right) {

        // 因为有头节点也可能变化，所以为了统一逻辑，这里设置虚拟节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        // 1. 从虚拟节点走 left - 1 步，来到 left 节点的前一个节点
        // 如：left = 3，走 2 步，到 2 节点
        ListNode prev = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode leftNode = prev.next;

        // 2. 从 leftNode 节点开始走 right - left 步，来到 right 节点
        // 使用 rightNode 接替 leftNode，从而保证 leftNode 不再变化
        ListNode rightNode = leftNode;
        for (int i = 0; i < right - left; i++) {
            // for 循环里面移动 rightNode，而不是 leftNode
            // leftNode 位置一旦确定，其位置就不能改变了
            rightNode = rightNode.next;
        }
        // 需要记住 prev 和 post，为了将 leftNode 和 rightNode 反转后的链表串联
        ListNode post = rightNode.next;

        // 3. 切断得到 left 到 right 的子链表
        prev.next = null;
        rightNode.next = null;

        // 4. 反转 leftNode 到 rightNode => 单独抽取成一个方法
        reverseList(leftNode);

        // 5. 将反转后的子链表接回到原链表
        prev.next = rightNode;
        leftNode.next = post;

        return dummyNode.next;
    }

    // 反转链表-迭代
    private void reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

    // KeyPoint 方法二 头插法
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为有头节点也可能变化，所以为了统一逻辑，这里设置虚拟节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        // 待删除节点的 prev，一定是从 dummyNode 开始，否则就没有起到 dummyNode 的作用
        ListNode prev = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 记录当前节点，对应 leftNode
        ListNode curr = prev.next;
        // left 后面还有 right - left 节点数，每个节点都要往前插入，故循环 right - left 次
        for (int i = 0; i < right - left; i++) {
            // cur(leftNode) 一直没有变化，for 循环不断将 cur 的 next 前移
            // prev 指针也没有变化，为了串联前移的 next 节点
            ListNode next = curr.next;
            // 头插法 => 核心代码 => 类似于：逆时针调整指针
            curr.next = next.next;
            // next 节点 next 指针，每次都得连接到反转后的第一个节点(prev.next)
            next.next = prev.next;
            prev.next = next;
        }
        return dummyNode.next;

        // KeyPoint 以后刷力扣，先执行代码，没有错误，再去提交，而不是将提交当做执行代码
    }
}
