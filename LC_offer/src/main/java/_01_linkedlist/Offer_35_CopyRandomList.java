package _01_linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2022-08-26 12:48
 * @Version 1.0
 */
public class Offer_35_CopyRandomList {
    public static void main(String[] args) {

    }

    public static Node copyRandomList(Node head) {

        // 边界判断，一般链表的题目都需要判断头节点是否为空
        if (head == null) return null;

        // 从链表的头节点开始遍历
        Node cur = head;

        // 使用一一对应的哈希表结构 Map 存放已经创建的节点
        Map<Node, Node> map = new HashMap<>();

        // 遍历原链表
        // KeyPoint 第一次遍历是建立节点
        while (cur != null) {
            // 以原链表的节点为 Key，构建一个 Map，Map 的 Value 为一个新链表中的节点
            // 新节点的值 val 和原链表的值 val 一样
            // 但原链表中的每个节点都有 next 和 random 指针，而 Map 中的 Value 没有 next 和 random 指针
            Node newNode = new Node(cur.val);
            map.put(cur, newNode);

            // 再次从链表的头节点开始遍历
            cur = cur.next;
        }

        // KeyPoint 循环一遍之后,需要将指针重新指向头节点,不然是报错的
        //      特别小心!
        cur = head;

        // 遍历原链表
        // KeyPoint 第二次遍历建立节点之间的指针关系
        while (cur != null) {
            // 原链表节点 ----  新链表节点
            // key       ----  value
            // cur       ----  map.get(cur)

            // 0、在字典中找到一个 cur 为 key 对应的那个 value 值
            Node valueCur = map.get(cur);

            // KeyPoint 接下来，需要去寻找 valueCur 的 next 节点和 random 节点

            // 寻找 valueCur 的 next 节点
            // 1、获取当前节点 cur 在原链表中的 next 指针指向的节点
            Node keyNextNode = cur.next;

            // 2、在字典中找到以 keyNextNode 为 key 对应的那个 value 值
            Node valueNextNode = map.get(keyNextNode);

            // 3、那么新链表中的这个节点的 next 指针就是 valueNextNode
            valueCur.next = valueNextNode;

            // 寻找 valueCur 的 random 节点
            // 1、获取当前节点 cur 在原链表中的 random 指针指向的节点
            Node keyRandomNode = cur.random;

            // 2、在字典中找到以 valueRandomNode 为 key 对应的那个 value 值

            Node valueRandomNode = map.get(keyRandomNode);
            // 3、那么新链表中的这个节点的 next 指针就是 valueNextNode

            valueCur.random = valueRandomNode;

            // 遍历下去，查看下一个节点
            cur = cur.next;
        }

        // 原链表节点 ----  新链表节点
        // key      ----- value
        // cur      ----- map.get(cur)
        // head     ----- map.get(head)
        return map.get(head);
    }
}
