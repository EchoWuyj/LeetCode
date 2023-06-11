package alg_01_新手班_zcy.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:35
 * @Version 1.0
 */
public class Code04_ReverseNodesInKGroup {

    // https://leetcode.cn/problems/reverse-nodes-in-k-group/

    // k个节点的组内逆序调整
    //   把一个链表分成多组,每组K个节点
    //   每组组内链表反转,最后一组若不够k个节点,则不调整

    //      例如：a->b->c->d->e->f->g->h
    //           假如 k =3 则调整后的链表为
    //           c->b->a->f->e->d->g->h

    // KeyPoint 对于链表问题,先在纸上梳理清楚,再去写代码

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    // 该函数是换头函数,不会是void类型
    public static ListNode reverseKGroup(ListNode head, int k) {
        // start指向head
        ListNode start = head;
        // end节点位置
        ListNode end = getKGroupEnd(start, k);

        // 第一组都凑不齐
        if (end == null) {
            return head;
        }

        // 第一组凑齐了
        // 修改head为真正返回的节点(反转后的节点)
        // 作为函数的返回值
        head = end;
        reverse(start, end);

        // 记录经过反转之后,该组的结尾节点指针
        ListNode lastEnd = start;

        // 尾指针的下个节点不为null,则执行while循环
        // 第一组因为需要定义一些常量,所以需要在while循环之外写
        // 后续重复的部分则在while中实现,需要使用到外部变量
        while (lastEnd.next != null) {

            // 将上一组结尾节点的后一个节点定义下一组的开头节点
            start = lastEnd.next;
            end = getKGroupEnd(start, k);

            if (end == null) {
                // 找下一组的过程中,发现凑不齐k个,直接返回head
                return head;
            }
            reverse(start, end);

            // 修改上一组反转后的尾节点指针next指针,重新指向下一组反转后的头节点(end节点)
            lastEnd.next = end;

            // 修改上一组的结尾节点指针,此时指向下一组的start节点
            lastEnd = start;
        }

        return head;
    }

    // KeyPoint 单独独立的方法进行抽取
    public static ListNode getKGroupEnd(ListNode start, int k) {

        // --i 是先执行i=i-1,然后再使用i的值,这时的i值就是表达式--i的值
        // i-- 是先使用i的值作为表达式i--的值,然后执行i=i-1操作。

        // j=i--;  i先赋值给j,然后i在执行i=i-1;
        // k=--i;  i先执行i=i-1,然后再赋值给;

        //-------------------------------------------------------

        // --k != 0
        // 这里使用k--是不正确的,因为自身节点算一个,所以得先k减去1
        //    k=1时,--k=0 != 0,直接返回start即可,不能让start=start.next;

        // start != null
        // 没说链表不为null,需要判空,否则start.next也是空指针风险
        // start指针后移的过程,不能保证链表长度大于k
        // 所以需要start!=null保证,不够k个则返回null
        while (--k != 0 && start != null) {
            start = start.next;
        }
        // 返回指针后移后start,即为end的位置
        return start;
    }

    // KeyPoint 单独独立的方法进行抽取
    // 反转start到end链表(从链表某个范围进行反转)
    // 没有返回值,直接使用void
    public static void reverse(ListNode start, ListNode end) {

        // end指针后移一个节点
        // 为了保证while循环cur!=end,执行反转的操作包括end节点
        end = end.next;

        // 反转链表需要的两个指针pre和next;
        ListNode pre = null;
        ListNode next = null;

        // cur从start开始
        ListNode cur = start;

        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            // 注意是当前节点,不是start节点
            // cur节点时不断变化的
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}
