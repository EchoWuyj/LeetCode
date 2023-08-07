package alg_02_train_dm._20_day_数据结构设计_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 9:48
 * @Version 1.0
 */
public class _01_155_MinStack4 {

    // KeyPoint 方法四 通过自定义链表实现栈 => 击败了 100.00% 的用户
    // => 链表表头作为栈顶，push 和 pop 时间复杂度 O(1)，因为存在 dummy，表头比较好操作
    //     dummy
    //       ↓
    //     |  | ← 栈顶
    //     |  |
    //     |__|
    // => 不使用系统已经有的栈实现，性能最好，速度最快，一般自己定义栈比使用系统栈速度要快
    // 类似：双端队列，自己手动实现比调用系统的
    class MinStack4 {
        class ListNode {
            int val;
            int min;
            ListNode next;

            public ListNode(int val, int min) {
                this.val = val;
                this.min = min;
            }

            // 在显示定义有参构造之后，也需要显示定义无参构造
            public ListNode() {
            }
        }

        // 虚拟头节点
        ListNode dummy;

        public MinStack4() {
            // 不使用系统栈
            // 初始化栈顶
            dummy = new ListNode();
        }

        public void push(int val) {
            int min = val;
            // 获取 head 节点
            ListNode head = dummy.next;
            // head 不为 null
            // 1.val 和链表表头的值(栈顶)进行比较，取小的值
            // 2.head.min 调用保证 head 不为 null
            if (head != null && head.min < val) {
                min = head.min;
            }
            // head 为 null，直接创建一个 ListNode 节点
            ListNode node = new ListNode(val, min);

            // KeyPoint 一般情况和特殊情况分别进行讨论，看代码是否都能满足，从而确定最后的代码

            // 链接 node next 指针 => 设置新的栈顶
            node.next = dummy.next;
            // 设置表头节点(移动栈顶指针)
            dummy.next = node;
        }

        public void pop() {
            ListNode head = dummy.next;
            // 表头不为空 => 栈不为空
            if (head != null) {
                // 删除栈顶元素
                dummy.next = head.next;
                head.next = null;
            }
        }

        public int top() {
            return dummy.next.val;
        }

        public int getMin() {
            return dummy.next.min;
        }
    }
}
