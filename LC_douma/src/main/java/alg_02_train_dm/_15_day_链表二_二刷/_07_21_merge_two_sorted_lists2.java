package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 20:02
 * @Version 1.0
 */
public class _07_21_merge_two_sorted_lists2 {

    // KeyPoint 方法二 递归实现
    // 满足递归 3 条件
    // 1.大问题 => 拆分小问题
    // 2.小问题求解和大问题求解一样
    // 3.存在最小子问题
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 递归边界
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // 根据 val 值大小，拆分子问题
        if (list1.val <= list2.val) {
            // 递的过程 => 不断拆解子问题 => list1.next 和 list2 组成新的两个链表
            // mergeTwoLists 返回值 => 两个链表合并之后的头节点
            // list1 的 next 指针指向：两个链表合并之后的头节点
            list1.next = mergeTwoLists(list1.next, list2);
            // 归的过程 => 不断将链表表头返回上层
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    // KeyPoint  递归实现 =>  自己实现 => 存在 bug => 已经解决
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        return process(l1, l2);
    }

    public static ListNode process(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            // KeyPoint 解决 bug
            // 1.不能将 cur 定义全局变量，否则，每次递归调用时，都会重新赋值给 cur 一个新的节点
            //   这会导致丢失之前的节点链接，无法正确地构建链表
            // 2.正确做法：每次都是创建一个新 cur，然后将头节点返回
            ListNode cur = new ListNode(l1.val);
            cur.next = process(l1.next, l2);
            return cur;
        } else {
            ListNode cur = new ListNode(l2.val);
            cur.next = process(l1, l2.next);
            return cur;
        }

        // cur 是 if 判断的局部变量，无法在 if 之外直接返回，只能在 if 里面返回
        // return cur;
    }
}
