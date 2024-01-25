package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 19:44
 * @Version 1.0
 */

public class _05_876_middle_of_the_linked_list {
    /*
        876. 链表的中间结点
        给你单链表的头结点 head ，请你找出并返回链表的中间结点(中间结点)。
        如果有两个中间结点，则返回第二个中间结点。

        示例 1：
        输入：head = [1,2,3,4,5]
        输出：[3,4,5]
        解释：链表只有一个中间结点，值为 3

        KeyPoint 特别注意：
        根据测试用例显示，本题要求的输出中间结点，其依然存在链表关系，所以输出为链表形式，而不是单个节点；
        若要输出单是中间结点值，则 middleNode.val;

        示例 2：
        输入：head = [1,2,3,4,5,6]
        输出：[4,5,6]
        解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。

        提示：
        链表的结点数范围是 [1, 100]
        1 <= Node.val <= 100

     */

    // KeyPoint 链表的中间结点 => 其他算法题基础
    // KeyPoint 方法一 两次遍历
    // 时间复杂度 O(n)
    public static ListNode middleNode1(ListNode head) {

        if (head == null || head.next == null) return head;
        // 1. 计算链表的长度
        int len = 0;
        // KeyPoint 本题不涉及节点增删操作，只是单纯访问，故不需要 dummy 节点
        ListNode cur = head;
        // cur 经过链表一轮遍历，cur 已经在链表尾部位置
        while (cur != null) { // O(n)
            len++;
            cur = cur.next;
        }

        // 2. 找到链表的中间节点
        for (int i = 0; i < len / 2; i++) { // O(n/2)
            // cur 经过链表一轮遍历，cur 已经在链表尾部位置
            // 故只能只使用 head 遍历到链表中间位置
            head = head.next;
        }
        return head;

        // KeyPoint 数组中间节点和链表中间节点
        // KeyPoint 关键：区分 '中点位置索引' 和 '到中点位置需要走的步数'

        // nums，len = 7 => 奇数
        // index 0 1 2 3 4  5 6
        // value 1 5 2 9 10 3 8
        //             ↑
        //            mid = len/2 = 7/2 = 3

        // nums，len = 6 => 偶数
        // index 0 1 2 3 4  5
        // value 1 5 2 9 10 3
        //             ↑
        //            mid = len/2 = 6/2 = 3 => 靠右

        // 将链表看成数组，位置索引也是从 0 开始
        // 总结：数组/链表 mid = len/2 => 中间节点位置索引

        // 链表
        // index 0 1 2 3 4  5 6
        // value 1 5 2 9 10 3 8
        //       ↑     ↑
        //     start  mid  => 从 start 到 mid 一共移动 len/2 次

    }

    // KeyPoint 方法二 快慢指针
    // 时间复杂度 O(n)
    // 该算法非常重要，该算法是其他链表算法基础
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        // KeyPoint 中点 mid 靠右
        // slow 和 fast 都是从 head 开始，slow 中点是靠右[后右]
        // fast 先走一步，slow 中点靠左[先左]

        // 数组
        // index 0 1 2 3 4  5
        // value 1 5 2 9 10 3
        //             ↑
        //            mid

        // slow 每次走一步
        ListNode slow = head;
        // fast 每次走两步
        ListNode fast = head;

        // KeyPoint 快慢指针一次遍历
        // 循环结束条件：fast == null || fast.next == null
        // => 最后 fast 指针要么在链表结尾节点，要么在结尾节点后一个为 null 位置
        // => 因为 fast 一次走两步，所以最后不确定 fast 结束位置，所以才有两种可能
        // 1.fast == null => 链表为偶数
        // 2.fast.next == null  => 链表为奇数
        // 相反逻辑 => 循环执行条件 fast != null && fast.next != null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
