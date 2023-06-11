package alg_01_新手班_zcy.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:35
 * @Version 1.0
 */
public class Code05_AddTwoNumbers {

    // https://leetcode.cn/problems/add-two-numbers/

    // 测试LeetCode需要将head1,head2替换成l1和l2

    // 两个链表相加求和
    //      给定两个链表的头节点head1和head2
    //      认为从左到右是某个数字从低位到高位返回相加之后的链表
    //      例子
    //      head1  4->3->6
    //      head2  2->5->3
    //     --------------------
    //             6->8->9
    //      从低位往高位加
    //      解释 634+352=986

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {

        // head1和head2已经是非空了,不需要判空判断

        int len1 = listLength(head1);
        int len2 = listLength(head2);

        // 长短链表重新定向
        // 长链表归l(long)
        // 短链表归s(short)

        // 比较len1和len2
        ListNode l = (len1 >= len2) ? head1 : head2;
        // 在l==head1情况下,s=head2
        ListNode s = (l == head1) ? head2 : head1;

        // 整个过程分3步走
        // (1) L有,S有
        // (2) L有,S无
        // (3) L无,S无

        //  3->4->6->1
        //  7->9->7

        // 遍历过程移动的节点
        // 最开始指向head1和head2
        ListNode curL = l;
        ListNode curS = s;

        // 表示进位
        int carry = 0;
        // 表示数值
        int curNum = 0;

        // 用来记录修改后长链表最后一个位置,用来给进位使用的
        ListNode last = curL;

        // (1) L有,S有
        while (curS != null) {
            curNum = curS.val + curL.val + carry;
            // KeyPoint 注意事项
            //  不重新创建一条新链表来保存两个链表相加的结果值,只是在长链表中修改value

            // %10得到的是/10余数(两个小o是多余的)
            curL.val = curNum % 10;
            // 除法/10得到是十位
            carry = curNum / 10;

            // last不断跟踪curL,从头节点开始
            // KeyPoint last 只是跟着curL即可,不需要last = curL.next;
            last = curL;

            // L和S链表的当前位置同时往后移一位
            curL = curL.next;
            curS = curS.next;
        }

        // (2) L有,S无
        // 从上个while出来就意味着短链表结束了,同时长链表没有结束
        while (curL != null) {
            curNum = curL.val + carry;
            curL.val = curNum % 10;
            carry = curNum / 10;
            // last不断跟踪curL
            last = curL;
            curL = curL.next;
        }

        // (3) L无,S无
        // 长链表和短链表都结束了,判断是否有进位
        // 如果进位信息不为0,则需要补一位进位1
        // KeyPoint 最后这里是if判断,不能是while循环,否则一直执行
        if (carry != 0) {
            last.next = new ListNode(1);
        }

        // 返回还是的链表形式返回数值的逆序
        return l;
    }

    // 求链表长度
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
