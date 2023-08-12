package alg_02_train_dm._18_day_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 11:39
 * @Version 1.0
 */
public class _07_109_convert_sorted_list_to_binary_search_tree1 {
    /*
        109. 有序链表转换二叉搜索树

        给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
        本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。

        输入: head = [-10,-3,0,5,9]
        输出: [0,-3,9,-10,null,5]
        解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。

        链表：-10 → -3 → 0 → 5 → 9

        二叉搜索树：
                 0
                / \
              -3   9
              /    /
            -10   5

        提示:
        head 中的节点数在[0, 2 * 104] 范围内
        -105 <= Node.val <= 105

     */

    // KeyPoint 方法一 分治
    public TreeNode sortedListToBST(ListNode head) {
        // null 表示链表尾节点后面一个位置
        return buildBST(head, null);
    }

    public TreeNode buildBST(ListNode left, ListNode right) {
        // 链表 [left,right) => 故 left == right 时，返回 null
        // 题目给定的链表为单向链表，访问后继元素十分容易，但无法直接访问前驱元素
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        // 确定中点
        TreeNode node = new TreeNode(mid.val);
        // [left,mid-1)
        TreeNode leftNode = buildBST(left, mid);
        // [mid+1,right)
        TreeNode rightNode = buildBST(mid.next, right);

        node.left = leftNode;
        node.right = rightNode;

        return node;
    }

    public ListNode getMedian(ListNode left, ListNode right) {

        // 链表：1 → 2 → 3 → 4

        // 靠左中点
        //   2
        //  / \
        // 1   3
        //      \
        //       4

        // 靠右中点
        //     3
        //    / \
        //   2   4
        //  /
        // 1

        // => 两种形式都是符合题目要求的

        ListNode slow = left;
        // slow 指向靠左中点
        ListNode fast = left.next;
        // slow 指向靠右中点
        // ListNode fast = left;

        // KeyPoint 举例验证代码
        // 通过简单例子来验证，避免太复杂而容易出错
        // 如：1 → 2 → 3 → 4 和 1 → 2 → 3 → 4 → 5

        // KeyPoint 区别：两种链表找中点方式
        // 1.只知道链表 head 节点，不知道尾节点，找其中点
        //   while (fast != null && fast.next != null)

        // 本题不能这样写，因为 right 在递归调用中没有变化，始终为刚开始传入的 null
        // 导致 buildBST(left, mid) 找的中点一直是以 left 开头原始链表的中点
        // 从而形成无限循环

        // 2.在给定区间上 [left,right] 上找中点
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    class ListNode {
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
