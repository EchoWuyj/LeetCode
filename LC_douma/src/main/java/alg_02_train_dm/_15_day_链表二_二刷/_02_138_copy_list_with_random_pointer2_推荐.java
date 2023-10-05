package alg_02_train_dm._15_day_链表二_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 23:51
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer2_推荐 {

    // 映射关系
    private Map<Node, Node> map = new HashMap<>();

    // KeyPoint 方法一 递归解法
    // 递归含义：复制带随机指针的链表，并返回链表头节点 Node
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 递的过程：创建新的节点，并且拷贝对应的值
        // KeyPoint 创建的是 Node 节点
        Node cloneNode = new Node(head.val);

        // KeyPoint 递的过程：维护'原节点'和'克隆节点'映射关系
        map.put(head, cloneNode);

        // 归的过程：维护 cloneNode 的 next 和 random 指针
        cloneNode.next = copyRandomList(head.next);

        // KeyPoint 归的过程：维护 random 指针
        // 虽然'原节点 head.random'对应'克隆节点'可能还没有出栈，但通过
        // map 映射关系可以获取'克隆节点'引用，从而可以设置 random 指针
        cloneNode.random = map.get(head.random);
        return cloneNode;
    }

    // KeyPoint 方法二 迭代解法 => 一个节点一个节点处理
    public Node copyRandomList1(Node head) {
        // head 为 null，直接返回 null
        if (head == null) return null;
        Node cloneNode = new Node(head.val);

        // Map 维护两层关系
        // 1.'原节点'和'克隆节点'映射关系
        // 2. 克隆链表中的节点已经被克隆，若能从 Map 中直接获取克隆节点，则不需再去克隆了
        Map<Node, Node> map = new HashMap<>();
        map.put(head, cloneNode);

        // 记录头节点，最后返回
        Node cloneHead = cloneNode;

        // KeyPoint 如何确定 while 循环条件
        // 一开始 while 条件不确定，可以暂时不写
        // 等将 while 循环体代码写完，再去确定 while 执行的条件
        while (head != null) {

            // 将创建克隆节点的方法单独抽取
            cloneNode.next = getCloneNode(head.next, map);
            cloneNode.random = getCloneNode(head.random, map);

            // KeyPoint 指针一定要移动，否则一直在原地打转，没有前进 => 尤其是链表问题
            head = head.next;
            cloneNode = cloneNode.next;
        }
        // KeyPoint 尽量区别变量命名
        // cloneHead 和 cloneNode 很容混淆，尽量避免
        // 比如：realHead 和 cloneNode
        return cloneHead;
    }

    // 方法含义：创建克隆节点
    // 基本思路：
    // 1.获取'原节点'的 next 或者 random 指向的节点，判断其是否在 Map 中存在
    // 2.若不存在，则先克隆，并将其放入 Map 中
    // 3.若存在，直接从 Map 中获取，避免重复克隆
    private Node getCloneNode(Node node, Map<Node, Node> map) {

        // 判空，链表中存在 null 节点 => 否则后续调用存在空指针异常
        if (node == null) return null;

        // 1.不存在，先克隆
        if (!map.containsKey(node)) {
            map.put(node, new Node(node.val));
        }

        // 2.存在，直接从 map 中拿
        // KeyPoint 没有使用 else 语句，最后返回 return
        return map.get(node);

        // KeyPoint 易错点：if else 语句
        // if else 另外一种实现，但是 if 语句中不要忘记 return
//        if (!map.containsKey(node)) {
//            map.put(node, new Node(node.val));
//            return map.get(node);
//        } else {
//            return map.get(node);
//        }
    }
}
