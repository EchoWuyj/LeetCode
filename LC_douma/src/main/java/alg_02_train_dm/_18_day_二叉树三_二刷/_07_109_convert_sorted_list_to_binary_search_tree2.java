package alg_02_train_dm._18_day_二叉树三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 15:02
 * @Version 1.0
 */
public class _07_109_convert_sorted_list_to_binary_search_tree2 {

    // KeyPoint 方法二 分治 + 中序遍历优化
    // 方法一时间复杂度瓶颈：在于寻找中位数节点
    // 由于构造出的二叉搜索树的中序遍历结果就是链表本身，因此可以将分治和中序遍历结合起来，减少时间复杂度

    // 遍历链表指针
    private ListNode cur;

    public TreeNode sortedListToBST(ListNode head) {
        cur = head;
        int len = getLength(head);
        return buildBST(0, len - 1);
    }

    public int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public TreeNode buildBST(int left, int right) {
        // [left,right] 两端都包括，故 if 判断条件 left > right
        if (left > right) {
            return null;
        }

        // 思路：
        // 中序遍历的顺序是'左中右'，在分治的过程中
        // 不必找出链表的中位数节点，而是使用一个占位节点，等到中序遍历到该节点时，再填充它的值。

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode();
        TreeNode leftNode = buildBST(left, mid - 1);
        root.left = leftNode;

        // 中序遍历中执行的操作
        root.val = cur.val;
        cur = cur.next;

        TreeNode rightNode = buildBST(mid + 1, right);
        root.right = rightNode;
        return root;
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
