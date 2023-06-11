package alg_03_leetcode_top_zcy.class_03_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-16 15:57
 * @Version 1.0
 */

public class problem_019_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 方法返回值得是新的头结点,因为倒数N个,可能会将头节点去掉
        // 注意:删除倒数N节点,需要知道倒数N节点的前一个节点

        /*
          1) 删除倒数N节点,则在遍历时先数够(N+1)个节点cur,此时使用指针del记录删除节点的前一个节点
               x, x, x, x, x,...x  N=4
              del        cur
          2) 后续del指针和cur指针同步移动,直到cur移到链表末尾,则del为要删除的倒数第(N+1)个节点
               x, x, ... x, x, x, x, x
                        del         cur
          3) 将其next指针,指向倒数第3个节点,这样倒数第4个节点就被删除了
               x, x, ... x,    x, x, x
                        del         cur
         */

        ListNode cur = head;
        ListNode del = null;

        // 判断删除的节点是否足够
        boolean flag = false;

        // 遍历链表进行判断
        while (cur != null) {
            n--;
            if (n <= 0) {
                if (n == 0) {
                    // 标记n数够了,但是del指针没有设置
                    flag = true;
                } else if (n == -1) {
                    // 在到n+1个节点之后,再去设置del指针
                    del = head;
                } else {
                    // n<-1时,del和cur一并向前移动
                    del = del.next;
                }
            }
            // cur指针不断后移
            cur = cur.next;
        }

        // !flag整体是true,则flag为false
        // 链表节点个数不够倒数第n个节点,直接返回原始的头节点
        if (!flag) {
            return head;
        }

        // cur到了最后一个节点,但是del还是null,del还没有移动
        // 删除的倒数n节点就是head节点
        if (del == null) {
            // 返回head的下个节点作为head
            return head.next;
        }

        // 删除倒数第N个节点的操作
        del.next = del.next.next;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
