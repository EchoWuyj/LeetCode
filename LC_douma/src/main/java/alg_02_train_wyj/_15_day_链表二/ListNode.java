package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 18:31
 * @Version 1.0
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    // 打印 cur 及其往后节点
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode curr = this;
        while (curr != null) {
            sb.append(curr.val).append("->");
            curr = curr.next;
        }
        sb.append("null");
        return sb.toString();
    }

    // 从数组中构建链表
    public static ListNode fromArray(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        // 创建 head 节点，保证其固定不懂
        ListNode head = new ListNode(arr[0]);
        // curr 遍历指针
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            // curr 后移
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{23, 12, 11, 34});
        // 打印 head ，默认调用 toString 方法，打印 head 及其完后的节点
        System.out.println(head); // 23->12->11->34->null
    }
}
