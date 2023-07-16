package alg_02_train_dm._15_day_链表二_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:36
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer1 {
       /*
            138. 复制带随机指针的链表
            给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，
            该指针可以指向链表中的 任何节点 或 空节点。

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

            示例 1：
            输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
            输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

            提示
            0 <= n <= 1000
            -10000 <= Node.val <= 10000
            Node.random 为空（null）或指向链表中的节点。
     */

    // 前置题目：拷贝单向链表
    // KeyPoint 方法一 迭代解法
    public static ListNode copyLinkedList(ListNode head) {

        // 链表题目，先通过具体例子，根据具体例子中模拟操作，再去使用代码实现

        // oldNode
        //   ↓
        //   1 → 2 → 3 → 4 → null
        //   ↑
        // head

        //   1
        //   ↑
        // cloneNode

        if (head == null) return null;
        ListNode oldNode = head;
        // 克隆值
        ListNode cloneNode = new ListNode(oldNode.val);
        // 记录克隆后链表表头
        ListNode newHead = cloneNode;

        // 创建克隆节点时，涉及 oldNode.next.val，为了避免空指针异常
        // 故需要对 oldNode 和 oldNode.next 都得判空
        while (oldNode != null && oldNode.next != null) {

            // KeyPoint 注意：创建克隆节点和移动指针逻辑先后顺序
            // 1.先创建克隆节点，oldNode.next.val，
            // 2.再去移动 oldNode 指针，到 oldNode.next 位置
            //  => 故 while 判断条件，oldNode.next == null，结束 while 循环
            //     此时，克隆节点 oldNode.next.val 已经创建了

            // 创建克隆节点
            // 调用 oldNode.next.val，不会空指针异常
            cloneNode.next = new ListNode(oldNode.next.val);
            // 移动指针
            oldNode = oldNode.next;
            cloneNode = cloneNode.next;
        }
        return newHead;
    }

    // 前置题目：拷贝单向链表
    // KeyPoint 方法二 递归解法 => 系统栈
    // 链表天然是符合递归的数据结构，链表问题一般都是可以使用递归实现
    // 递归特点
    // 1.大问题 => 小问题
    // 2.小问题解决方式和大问题一样
    // 3.存在最小子问题
    public static ListNode copyLinkedList1(ListNode head) {
        // 递归边界，不能再递归了，往上层返回
        if (head == null) return null;

        // 递的过程：克隆原链表，拆分 => 当前节点 cur 和 剩余链表 rest
        // 克隆当前节点
        ListNode cur = new ListNode(head.val);

        // 返回已经克隆好的剩余链表 rest 的头节点
        ListNode rest = copyLinkedList(head.next);

        // 归的过程：cur 串联 rest 的头节点
        cur.next = rest;

        // 将克隆当前节点返回给上层
        // 若 cur 是一开始的头节点，则最后返回则是链表头节点
        return cur;
    }

    // 前置题目：拷贝单向链表
    // KeyPoint 方法三 显示栈
    public static ListNode copyLinkedList2(ListNode head) {
        if (head == null) return null;

        // 1 -> 2 -> 3 -> 4
        // 在克隆节点 1 的值，但是节点 1 指针关系还不确定，故先暂存，后面再七处理
        // => 先进后出 => 栈

        Deque<ListNode> stack = new ArrayDeque<>();

        // KeyPoint 核心
        //  1.递的过程 => 压栈
        //  2.归的过程 => 出栈

        // 递的过程 => 压栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        // 定义 cloneNode 指针，用于记录新链表的头节点
        ListNode cloneNode = null;
        // 归的过程 => 出栈
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            ListNode cur = new ListNode(pop.val);
            // KeyPoint 链表头插入，从而实现逆序
            //     6 → null
            //     ↑     ↑
            //    cur cloneNode
            //     ↑
            //  cloneNode'
            cur.next = cloneNode;
            cloneNode = cur;
        }
        return cloneNode;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        System.out.println(copyLinkedList(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList1(head)); // 1->2->3->4->null
        System.out.println("=========");
        System.out.println(copyLinkedList2(head)); // 1->2->3->4->null
    }
}
