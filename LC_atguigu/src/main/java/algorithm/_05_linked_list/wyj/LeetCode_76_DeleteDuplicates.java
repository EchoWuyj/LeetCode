package algorithm._05_linked_list.wyj;

import algorithm._05_linked_list.atguigu.ListNode;

/**
 * @Author Wuyj
 * @DateTime 2022-08-28 12:36
 * @Version 1.0
 */
public class LeetCode_76_DeleteDuplicates {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        print(l1);
        ListNode resultNode = deleteDuplicates02(l1);
        print(resultNode);
    }

    public static void print(ListNode listNode) {
        ListNode cur = listNode;

        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // KeyPoint 指针遍历
    public ListNode deleteDuplicates01(ListNode head) {
        // 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，
        // 因此我们只需要对链表进行一次遍历，就可以删除重复的元素。

        // head为null情况
        if (head == null) {
            return head;
        }

        ListNode cur = head;

        // 单个节点的情况,直接跳出while循环
        // 最后结尾的情况,cur指向最后一个节点,此时cur.next已经为null,while循环结束
        while (cur.next != null) {
            // 两个节点值相同
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                // 指针后移
                cur = cur.next;
            }
        }

        return head;
    }

    // KeyPoint 递归
    public static ListNode deleteDuplicates02(ListNode head) {
        /*
        递归套路解决链表问题：
            (1) 找终止条件：当head指向链表只剩一个元素的时候，自然是不可能重复的，因此return
            (2) 想想应该返回什么值：应该返回的自然是已经去重的链表的头节点
            (3) 每一步要做什么：
                宏观上考虑，此时head.next已经指向一个去重的链表了，而根据第二步，我应该返回一个去重的链表的头节点
                因此这一步应该做的是判断当前的head和head.next是否相等
                如果相等则说明重了，返回head.next，否则返回head
         */

        // 递归边界
        if (head == null || head.next == null) {
            // 递归边界返回头结点,并不是null
            return head;
        }

        // 使用cur指针指向head
        ListNode cur = head;

        // 使用递归直接跳到递归的最里层来分析考虑
        //  1 -> 1 ->  3 -> 4 -> 4 -> null
        // KeyPoint 重点分析:传入的参数&使用什么变量接受
        //          传入的参数:当前节点cur的下一个节点,故为cur.next
        //          使用什么变量接受:修改cur的next指针,此时向一个去重的链表,所以使用cur.next接受
        //      即使最开始有重复的11,也是先递归到最里层,先去判断44
        //      递归到 4 -> 4 -> null, 4 -> 4
        cur.next = deleteDuplicates02(cur.next);

        if (cur.val == cur.next.val) {
            // KeyPoint 注意事项
            //  修改节点方式和指针遍历方法中不一样
            //  链表题目,前面是指针,后面是节点
            cur = cur.next;
        }
        return cur;
    }
}


