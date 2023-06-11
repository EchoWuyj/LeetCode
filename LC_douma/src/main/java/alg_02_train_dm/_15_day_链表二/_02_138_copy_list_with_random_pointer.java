package alg_02_train_dm._15_day_链表二;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:36
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer {
       /*
            138. 复制带随机指针的链表
            给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，
            该指针可以指向链表中的任何节点或空节点。

            构造这个链表的深拷贝。
            深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
            新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
            并使原链表和复制链表中的这些指针能够表示相同的链表状态。
            复制链表中的指针都不应指向原链表中的节点

            例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
                  那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

            返回复制链表的头节点。

            用一个由 n 个节点组成的链表来表示输入/输出中的链表。
            每个节点用一个 [val, random_index] 表示：
                val：一个表示 Node.val 的整数。
                random_index：随机指针指向的节点索引（范围从 0 到 n-1）
                如果不指向任何节点，则为  null 。

            你的代码 只 接受原链表的头节点 head 作为传入参数。

            提示
            0 <= n <= 1000
            -10000 <= Node.val <= 10000
            Node.random 为空（null）或指向链表中的节点。
     */

    // KeyPoint 拷贝单向链表 方法一 迭代解法
    public static ListNode copyLinkedList(ListNode head) {
        if (head == null) return null;
        ListNode oldNode = head;
        // 克隆值
        ListNode newNode = new ListNode(oldNode.val);
        // 记录克隆后链表表头
        ListNode newHead = newNode;

        // 因为涉及克隆值和指针关系，故需要对 oldNode 和 oldNode.next 都得判空
        while (oldNode != null && oldNode.next != null) {
            // 克隆指针关系
            // oldNode.next != null，调用 oldNode.next.val，不会空指针异常
            newNode.next = new ListNode(oldNode.next.val);

            // 移动指针
            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return newHead;
    }

    // KeyPoint 拷贝单向链表 方法二 递归解法(系统栈)
    // KeyPoint 链表天然是符合递归的数据结构，链表问题一般都是可以使用递归实现
    public static ListNode copyLinkedList1(ListNode head) {
        // 递归边界，不能再递归了，往上层返回
        if (head == null) return null;

        // 递的过程：克隆原链表 拆分 => 当前节点 curNode 和 剩余链表 rest
        // 克隆当前节点
        ListNode curNode = new ListNode(head.val);

        // 返回已经克隆好的剩余链表 rest 的头节点
        ListNode p = copyLinkedList(head.next);

        // 归的过程：curNode 串联 rest 的头节点
        curNode.next = p;

        // 将克隆当前节点返回给上层
        return curNode;
    }

    // KeyPoint 拷贝单向链表 方法三 显示栈
    public static ListNode copyLinkedList2(ListNode head) {
        if (head == null) return null;

        // 1 -> 2 -> 3 -> 4
        // 克隆节点 1 的值，但是节点 1 指针关系还不确定，故先暂存，后面处理
        // => 先进后出 => 栈

        ArrayDeque<ListNode> stack = new ArrayDeque<>();

        // KeyPoint 核心
        //  1.递的过程 => 压栈
        //  2. 归的过程 => 出栈

        // 递的过程 => 压栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        // 定义 newHead 指针，用于记录新链表的头节点
        ListNode newHead = null;
        // 归的过程 => 出栈
        while (!stack.isEmpty()) {
            ListNode top = stack.pop();
            ListNode curNode = new ListNode(top.val);
            curNode.next = newHead;
            newHead = curNode;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
    }

    // 映射关系
    private Map<Node, Node> map = new HashMap<>();

    // KeyPoint 方法一 递归解法
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 递的过程：创建新的节点，并且拷贝对应的值
        Node curNode = new Node(head.val);
        // 维护'原节点'和'克隆节点'映射关系
        map.put(head, curNode);

        // 归的过程：维护 next 和 random 指针
        curNode.next = copyRandomList(head.next);
        // 虽然'原节点 head.random'对应'克隆节点'可能还没有出栈，但通过
        // map 映射关系可以获取'克隆节点'引用，从而可以设置 random 指针
        curNode.random = map.get(head.random);

        return curNode;
    }

    // KeyPoint 方法二  迭代解法
    public Node copyRandomList1(Node head) {
        if (head == null) return null;

        Node oldNode = head;
        Node newNode = new Node(oldNode.val);

        // Map 维护两层关系
        // 1.'原节点'和'克隆节点'映射关系
        // 2.链表中节点已经被克隆，若能从 Map 中直接获取，则不需再去克隆了
        Map<Node, Node> map = new HashMap<>();
        map.put(oldNode, newNode);

        Node newHead = newNode;
        while (oldNode != null) {
            // oldNode.next != null 不能在 while 循环中，若 oldNode.next == null
            // 但 random != null，会跳过这种情况，出现 bug
            // 此外 oldNode.next == null，但是已经在 getCloneNode 方法中做了判空
            // KeyPoint 注意是 oldNode.next，不是 next.next
            newNode.next = getCloneNode(oldNode.next, map);
            newNode.random = getCloneNode(oldNode.random, map);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        // KeyPoint 注意命名方式，newHead 和 newNode 很容混淆，尽量避免，比如：realHead，newNode
        return newHead;
    }

    // 获取克隆节点
    // 基本思路：
    // 1.获取'原节点'的 next 或者 random 指向的节点，判断其是否在 Map 中存在
    // 2.若不存在，则先克隆，并将其放入 Map 中
    // 3.若存在，直接从 Map 中获取，避免重复克隆
    private Node getCloneNode(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        // 不存在，先克隆
        if (!map.containsKey(node)) {
            map.put(node, new Node(node.val));
        }
        // 存在，直接从 map 中拿
        return map.get(node);
    }

    // KeyPoint 方法三 用新旧节点交替的方式，模拟 map 的功能，节省空间
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        // 1. 新旧节点交替
        // 创建新节点，并且放在旧节点的后面
        // 假设原先链表是这样：A->B->C，那么创建新节点后，链表变成：A->A'->B->B'->C->C'
        // 其中 A' 是 A 的克隆节点
        Node curr = head;
        // 遍历原链表
        while (curr != null) {
            Node newNode = new Node(curr.val);
            // newNode 插入 curr 和 curr.next 节点之间
            newNode.next = curr.next;
            curr.next = newNode;
            // 移动 curr 到原链表下个节点，即 newNode.next
            curr = newNode.next;
        }

        // 2. 设置正确的 random
        curr = head;
        // 遍历原链表
        while (curr != null) {
            // 克隆节点 random 指针 -> 原节点 random 指针的下个节点
            // KeyPoint 凡是指涉，必有前提
            // curr.random.next => curr.random 需要判空
            // 原链表中有节点的 random 为 null
            curr.next.random = (curr.random != null) ? curr.random.next : null;
            // 每次向后移动两个节点
            curr = curr.next.next;
        }

        // 3. 分割新旧链表
        // 将 A->A'->B->B'->C->C' 切割成：A->B->C 和 A'->B'->C'
        Node currOld = head;
        Node currNew = head.next;
        // 克隆链表头节点
        Node newHead = head.next;
        while (currOld != null) {
            // A->B->C
//            currOld.next = currOld.next.next;
            // 为了严谨性，可以这样写，必然正确，不需要考虑边界情况
            currOld.next = (currOld.next != null) ? currOld.next.next : null;

            // A'->B'->C'
            // currNew 在 C'位置，则 currNew.next.next 空指针异常
            currNew.next = (currNew.next != null) ? currNew.next.next : null;

            // KeyPoint 若调整链表 next 指针，则链表后一个节点就已经更新了，不能再使用原链表了
            // 如：currOld.next = (currOld.next != null) ? currOld.next.next : null;
            // currOld 的 next 指针已经调整过，其后面一个节点为 currOld.next.next，故不在是原来的链表了

            // A->A'->B->B'->C->C'
            // A -> B
            // A'-> B'
            currOld = currOld.next;
            currNew = currNew.next;
        }
        return newHead;
    }
}
