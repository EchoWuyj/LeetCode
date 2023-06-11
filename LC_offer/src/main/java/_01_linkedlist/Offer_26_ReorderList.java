package _01_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 0:57
 * @Version 1.0
 */
public class Offer_26_ReorderList {
    public void reorderList(ListNode head) {

        // 判空
        if(head ==null || head.next ==null || head.next.next==null){
            return;
        }

        // 找中点
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast.next !=null && fast.next.next != null){
            fast= fast.next.next;
            slow = slow.next;
        }

        // 此时slow是中点
        fast = slow.next;
        slow.next = null;
        ListNode help = null;

        // 反转
        while(fast !=null){
            help= fast.next;
            fast.next = slow;
            slow = fast;
            fast=help;
        }

        // 合并
        fast = head;
        ListNode tempFast = null;

        while(fast != null && slow!=null){
            // 记录前面节点
            tempFast = fast.next;
            help = slow.next;

            // 修改指针
            fast.next=slow;
            slow.next= tempFast;
            fast = tempFast;
            slow=help;
        }
    }
}
