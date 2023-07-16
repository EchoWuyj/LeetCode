package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 17:05
 * @Version 1.0
 */
public class _10_148_sort_list2 {

    // KeyPoint 方法二 归并排序 迭代实现(自底朝上)
    // 对算法掌握要求比较高
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        // 计算链表的长度
        // KeyPoint 严格遵守链表指针含义
        // 1.head 为头节点，尽量不要让 head 发生变化
        // 2.遍历链表使用 cur，而不是使用 head
        //   否则，head 经过循环，已经移动链表最后位置
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 两两合并，四四合并，八八合并 ...
        // 其中两两 => 整体长度为 2 => 子节点长度为 1，即 step = 1
        // step = 1,2,4,8...
        // 注意：step 从 1 开始，而不是从 0 开始
        // KeyPoint 位运算
        // 通过 += 联想 <<=，即 step <<= 1，不是简单的 <<，还得赋值
        for (int step = 1; step < len; step <<= 1) {

            // pre 指针用于串联，多个两两合并后的链表，最终成为一个完整链表
            // 下轮 for 循环，执行四四合并，pre 又从 dummy 开始，串联多个四四合并后的链表
            ListNode pre = dummy;
            System.out.println(dummy.val);

            // KeyPoint 易错点
            // 即使 head 没有移动，也是不能直接使用 head 的
            // 因为经过 merge 之后，链表顺序已经调整过，故头节点已经发生变化了
            // head 记录的还是最初的头节点，故不能使用
            // dummy.next 记录的是 merge 之后的头节点，故可以使用
            cur = dummy.next;

//            System.out.println("---");
//            System.out.println(cur.val); // 4
//            System.out.println(head.val); //4
//            System.out.println("---");

            // 两两合并
            // ---
            //  4
            //  4
            // ---

            // 四四合并
            // ---
            //  2
            //  4
            // ---

            //   ___________
            //  |          |
            // -1 → 5  3 → 4   0 → 2 → null
            //  ↑      ↑
            // left   right
            //  ↑       ↑      ↑
            // node    node   cur

            // 循环一轮，在原链表上完成两两合并，再去执行四四合并，依次类推
            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(left, step);

                // 更新 cur， cur 移动到下次处理的链表头
                // => 进行后续的两两合并，否则 while 循环结束不了
                cur = cut(right, step);

                // KeyPoint 链表串联的关键
                // 更新 pre
                // 1.merge 方法将 pre 作为形参传入，将上一轮合并后的最后位置，作为下轮开始位置
                // 2.合并 left 和 right 链表，并将合并后链表的最后一个节点返回给 pre
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;
    }

    // 两两合并，将两个长度为 1 的子节点切开，形成两个独立链表，再去 merge
    // split 函数功能：将 node 从第 step 个节点切断，并返回右边链表的头节点
    private static ListNode cut(ListNode node, int step) {
        if (node == null) return null;
        // 找到分割点，因为 node 本身就是一个节点，故 i 从 1 开始
        for (int i = 1; i < step; i++) {

            // KeyPoint 非空判断
            // if (node != null) node.next = null
            // => 单是从代码非空层面判断是不够的
            // ListNode right = (node == null) ? null : node.next;
            // if (node != null) node.next = null;
            // => 虽然后续代码，进行了非空处理，能保证代码运行不报错
            // => 但是不符合代码操作逻辑，代码执行起来存在 bug

            // node.next != null 表示 node 后面有节点，node 才进行后移操作，否则就保持不动
            //   2   4     1   3   → null
            //     ↑       ↑   ↑
            // 已经 merge  cur right => 在执行 cut(right, step) 确定 cur 时
            //                 ↑
            //                node => node.next 已经为 null，故 node 不用后移
            //                ListNode right = node.next = null;
            //                node.next = null
            //                后续，返回 right 作为 cur，而 cur 为 null，结束 while 循环
            if (node.next != null) node = node.next;

            // KeyPoint 简化 if 写法
            // => node.next != null 加入到 for 循环中，作为循环条件
            // => node.next != null && i < step
        }
        // right 指针记录，切分后右侧链表的头节点
        ListNode right = node.next;
        // 切断联系，独立成为一个子数组
        node.next = null;
        // 返回右边链表的头节点
        return right;
    }

    // 合并操作
    // 1.合并 left 和 right 两个有序链表
    // 2.将 pre.next 设置为合并之后链表的表头
    // 3.返回合并之后，链表的最后一个节点
    private static ListNode merge(ListNode left, ListNode right, ListNode pre) {
        // 本轮合并，接着上轮合并最后位置 pre 开始
        // => 从而实现多组两两合并后面链表的串联
        ListNode cur = pre;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left == null) cur.next = right;
        if (right == null) cur.next = left;

        // 为了保证 cur 是合并之后链表的最后一个节点
        // => 必须执行 while (cur.next != null) cur = cur.next;
        // 解释说明：
        // 一个链表有节点，另一个链表没有节点，则 while 循环没有执行
        // 只是执行了 cur.next = right 或者 left，cur 链接上其中一条链表
        // 但此时 cur 不在合并链表的最后，故需要通过移动 cur 到链表的最后

        // 注意：使用 cur.next，保证跳出 while 循环，cur 是最后一个
        // KeyPoint 想让 cur 为最后一个节点，则 while 循环条件，一般是 cur.next
        while (cur.next != null) cur = cur.next;
        return cur;
    }

    // for test
    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{4, 2, 1, 3});
        ListNode res = sortList(head);
        printLinkedList(res);
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
