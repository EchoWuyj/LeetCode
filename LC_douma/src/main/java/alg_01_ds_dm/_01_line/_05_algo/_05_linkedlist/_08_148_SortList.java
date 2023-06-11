package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 15:43
 * @Version 1.0
 */
public class _08_148_SortList {

    /*
        148. 排序链表
        给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

        示例 1：
        输入：head = [4,2,1,3]
        输出：[1,2,3,4]

        示例 2：
        输入：head = [-1,5,3,4,0]
        输出：[-1,0,3,4,5]

        提示
        链表中节点的数目在范围 [0, 5 * 104] 内
        -105 <= Node.val <= 10^5
     */

    // KeyPoint 方法一 归并排序 递归实现(自顶朝下)
    public ListNode sortList1(ListNode head) {
        // KeyPoint 递归边界
        // 递归边界，不仅仅是 head == null，head.next == null 即只有一个元素时，也是递归边界需要返回
        // 以后链表的特判，最好都是固定这样写，比较保险
        if (head == null || head.next == null) return head;

        // 找到中间节点 => 归并排序中间节点需要保证'左中点'，不能是'右中点'
        // 所以这里 fast 先走一步，否则 slow 最后就是在'右中点'位置，不符合要求
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            // 每次走两步
            fast = fast.next.next;
        }

        // 将中间以右断开，形成两个链表
        ListNode rightHead = slow.next;
        slow.next = null;

        // 对左边链表排序，返回排完序链表的表头
        ListNode left = sortList1(head);
        // 对右边链表排序，返回排完序链表的表头
        // KeyPoint 变量命名 rightHead 和 rightSortedList 容易混淆，最好避免出现
        ListNode right = sortList1(rightHead);

        // 对左右两边排完序链表合并
        return mergeTwoLists(left, right);
    }

    // 合并有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // KeyPoint 使用 cur 串联剩余的链表，而不是直接 return l1 或者 l2
        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;

        return dummyNode.next;
    }

    // KeyPoint 方法二 归并排序 迭代实现(自底朝上)
    // 对算法掌握要求比较高
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 计算链表的长度
        int length = 0;
        while (head != null) {
            length++;
            // head 在 while 循环过程中，已经移动链表最后位置
            head = head.next;
        }

        // 两两(多个两两)合并，子数组长度为 1，即 step = 1
        // step = 1,2,4,8...
        // bug 修复：step 从 1 开始，而不是从 0 开始
        // KeyPoint step <<= 1，不是简单的 <<，还得赋值
        for (int step = 1; step < length; step <<= 1) {
            // 每个 step 都是存在一个 prev
            ListNode prev = dummy;
            // cur 从 head 节点开始，但是不能使用 head，而是 dummy.next
            ListNode curr = dummy.next;
            // 循环一轮，在原链表上完成两两合并，再去执行四四合并，依次类推
            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, step);

                // KeyPoint cur 和 prev 不断前移，故需要不断更新
                // cur 移动到下次处理的链表头 => 时刻记住：更新 cur
                curr = split(right, step);
                // 合并 left 和 right 链表，并将合并后链表的最后一个节点返回给 prev
                // => 时刻记住：更新 prev
                // KeyPoint 链表串联的关键
                // merge 方法将 prev 作为形参传入，将上一轮合并后的最后位置，作为下轮开始位置
                prev = merge(left, right, prev);
//                System.out.println(dummy.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{3, 2, 4, 1, 5, 6});
        System.out.println(sortList(head));
    }

    // 合并操作
    // 1.合并 left 和 right 两个有序链表
    // 2.将 prev.next 设置为合并之后链表的表头
    // 3.返回合并之后链表的最后一个节点
    private static ListNode merge(ListNode left, ListNode right, ListNode prev) {
        // 本轮合并，接着上轮合并最后位置开始，从而实现串联链表
        ListNode curr = prev;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        if (left == null) curr.next = right;
        if (right == null) curr.next = left;

        // 保证 curr 是合并之后链表的最后一个节点
        // 若：一个链表有节点，另一个链表没有节点，则 while 循环没有执行，
        // 此时 cur 不在合并链表的最后，故需要通过移动 cur 到链表的最后
        // KeyPoint 使用 cur.next，保证跳出 while 循环，curr是最后一个
        while (curr.next != null) curr = curr.next;
        return curr;
    }

    // split 函数功能：将 node 从第 step 个节点切断，并返回右边链表的头节点
    private static ListNode split(ListNode node, int step) {
        if (node == null) return null;
        // 找到分割点
        // left 本身就是一个节点，故 i 从 1 开始
        for (int i = 1; node.next != null && i < step; i++) {
            // node.next != null，node 指针才后移，否则没有意义
            // if (node.next != null)  node = node.next;
            // KeyPoint 另外一种写法 => node.next != null 加入到 for 循环中，作为循环条件
            node = node.next;
        }
        ListNode right = node.next;
        // 切断联系，独立成为一个子数组
        node.next = null;
        // 返回右边链表的头节点
        return right;
    }
}
