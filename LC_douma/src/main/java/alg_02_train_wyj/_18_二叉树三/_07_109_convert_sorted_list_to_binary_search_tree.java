package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 14:29
 * @Version 1.0
 */
public class _07_109_convert_sorted_list_to_binary_search_tree {

    public TreeNode sortedListToBST(ListNode head) {
        return buildBST(head, null);
    }

    public TreeNode buildBST(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode node = new TreeNode(mid.val);
        TreeNode leftNode = buildBST(left, mid);
        TreeNode rightNode = buildBST(mid.next, right);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode fast = left.next;
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
