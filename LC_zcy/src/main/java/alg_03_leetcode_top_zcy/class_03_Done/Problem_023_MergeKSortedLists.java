package alg_03_leetcode_top_zcy.class_03_Done;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-02-16 20:10
 * @Version 1.0
 */

// 合并K个升序链表
// 参考 Code01_MergeKSortedLists
public class Problem_023_MergeKSortedLists {
    /*
        给你一个链表数组,每个链表都已经按升序排列
        请你将所有链表合并到一个升序链表中,返回合并后的链表

        输入：lists = [[1,4,5],[1,3,4],[2,6]]
        输出：[1,1,2,3,4,4,5,6]
        解释：链表数组如下：
            [
             1->4->5,
             1->3->4,
             2->6
            ]
        将它们合并到一个有序链表中得到:1->1->2->3->4->4->5->6


        实现思路
        1) 先将各自链表的头节点加入优先级队列中(小根堆)
           小根堆:放进的其中的元素(元素可以重复),将里面的元素从小到大排序好
                 每次弹出都是堆中的最小值,此外加入和弹出都是O(logN)
        2) 再从小根堆中弹出节点值最小的节点,同时将该节点的next指针指向的下个节点,加入优先级队列
        3) 优先级队列自动调整节点值大小的排序,之后就依次循环弹出和加入
     */

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            // KeyPoint "按照顺序" 对应 "从小到大"
            // 从小到大排序,不是单纯的o1-o2,而是使用节点的val值相减
            return o1.val - o2.val;
        }
    }

    // 数组lists里面存储的是多个head节点
    public static ListNode mergeKLists(ListNode[] lists) {
        /*
           区别几种形式

           1.数组为null:是创建了数组的引用,栈中arr的内存空间没有存放任何地址
             String[] arr = null;

           2.数组为空:数组是空其实就是数组的长度为0,数组是真正的对象,只是对象中没有元素,也就是说里面没有内容
             arr.length== 0

           3.判断数组的每个元素都为空
             arr=[null,null,null]
         */

        // 特殊情况的判断
        if (lists == null) {
            return null;
        }

        // 优先级队列实现小根堆
        // 往优先级队列中加入自定义的ListNode,需要在new PriorityQueue(),加入比较器
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < lists.length; i++) {
            // KeyPoint 注意
            //  加入之间,先判空,因为优先级队列中不可以存储null,因为优先级
            //  队列中元素需要进行动态排序调整的,加入null,无法比较排序!
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        // KeyPoint 对引用方法调用的判空,避免代码出现异常
        // 1)数组引用不为null,但是数组为空的情况,arr=[null,null,null]
        //   此时,优先级队列中则什么元素都没有加入,则优先级队列为空
        // 2)同时,后面代码中heap.poll()使用到heap,同样需要判空,否则会报空指针异常
        if (heap.isEmpty()) {
            return null;
        }

        // heap第一弹出为节点值最小的节点,让其作为头节点
        ListNode head = heap.poll();

        // 串联节点的指针(起到head节点的作用),从头节点开始依次串联后面的节点
        // 为了保证head不发生变化,最后返回
        ListNode pre = head;

        // pre.next表示head链表的下个节点,不为null则加入heap中
        // pre指针本身是没有移动的
        if (pre.next != null) {
            heap.add(pre.next);
        }

        // KeyPoint 注意事项
        //  1)上面代码是处理第一个点,后续的节点可以使用while循环遍历
        //  2)在while循环外面定义需要的变量,后面重复操作在while中使用
        while (!heap.isEmpty()) {
            // 弹出节点
            ListNode cur = heap.poll();
            // 串联节点
            pre.next = cur;
            // KeyPoint pre串联的指针,一定需要记得后移,pre是动态变化的,pre后移
            pre = cur;
            // 将cur后面节点再加入
            // 将链表的节点加入到小根堆中,原链表的关系仍然存在,可以通过cur找到下个节点
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        // 最大值的next指针在原链表中就是null,因此不需要再手动设置;
        return head;
    }
}
