package alg_02_train_wyj._15_day_链表二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-15 14:17
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer2 {
    private Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cloneNode = new Node(head.val);
        map.put(head, cloneNode);

        cloneNode.next = copyRandomList(head.next);
        cloneNode.random = map.get(head.random);

        return cloneNode;
    }

    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node cloneNode = new Node(head.val);
        map.put(head, cloneNode);
        Node newHead = cloneNode;
        while (head != null) {
            cloneNode.next = getCloneNode(head.next, map);
            cloneNode.random = getCloneNode(head.random, map);
            cloneNode = cloneNode.next;
            head = head.next;
        }
        return newHead;
    }

    public Node getCloneNode(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        if (!map.containsKey(node)) {
            map.put(node, new Node(node.val));
        }
        return map.get(node);
    }
}
