package alg_02_train_wyj._15_day_链表二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-15 14:17
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer2 {

    // 映射关系
    private Map<Node, Node> map = new HashMap<>();

    // 递归
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cloneNode = new Node(head.val);
        map.put(head, cloneNode);
        cloneNode.next = copyRandomList(head.next);
        cloneNode.random = map.get(head.random);
        return cloneNode;
    }

    // 迭代
    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        Node cloneNoe = new Node(head.val);

        Map<Node, Node> map = new HashMap<>();
        map.put(head, cloneNoe);

        Node cloneHead = cloneNoe;

        while (head != null) {
            cloneNoe.next = getCloneNode(head.next, map);
            cloneNoe.random = getCloneNode(head.random, map);

            head = head.next;
            cloneNoe = cloneNoe.next;
        }
        return cloneHead;
    }

    public Node getCloneNode(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        if (!map.containsKey(node)) {
            map.put(node, new Node(node.val));
        }
        return map.get(node);
    }
}
