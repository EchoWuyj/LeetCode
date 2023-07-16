package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-15 14:41
 * @Version 1.0
 */
public class _02_138_copy_list_with_random_pointer3 {

    // KeyPoint 方法三 用新旧节点交替的方式 A->A'->B->B'->C->C'
    //                 在原始链表中，维护了新旧节点一一映射关系
    //                => 模拟 map 的功能，节省空间
    public Node copyRandomList2(Node head) {

        // 最好还是特判下 => 除非限制条件明确说明：head != null
        // 本题提示：0 <= n <= 1000，可能链表为 null，表示 0 个节点
        if (head == null) return null;

        // 操作流程
        // 1.新旧节点交替
        // 2.设置正确的 random
        // 3.分割新旧链表

        // 1.新旧节点交替
        // 创建新节点，并且放在旧节点的后面
        // 假设原先链表是这样：A->B->C，那么创建新节点后，链表变成：A->A'->B->B'->C->C'
        // 其中 A' 是 A 的克隆节点
        Node cur = head;
        // 遍历原链表
        while (cur != null) {
            Node newNode = new Node(cur.val);
            // newNode 插入 cur 和 cur.next 节点之间
            // 即在 A->B 之间插入 A'，即为 A->A'->B
            newNode.next = cur.next;
            cur.next = newNode;
            // 移动 cur 到原链表下个节点，即 newNode.next
            cur = newNode.next;
            //  A->A'->B
            //         ↑
            //        cur
        }

        // 2.设置正确的 random
        // cur 又从头开始遍历
        cur = head;
        // 遍历链表 => A->A'->B->B'->C->C'
        while (cur != null) {

            // 1.cur.next => 克隆节点
            //   cur.next.random => 克隆节点 random 指针
            // 2.cur => 原节点
            //   cur.random.next => 原节点 random 指针的下个节点
            // 3.注意：凡是指涉，必有前提
            //   cur.random 有可能为空，故需要判空
            cur.next.random = (cur.random != null) ? cur.random.next : null;

            // cur 每次向后移动两个节点
            //  A->A'->B->B'->C->C'
            //  ↑      ↑
            // cur    cur
            cur = cur.next.next;
        }

        // 3. 分割新旧链表
        // 将 A->A'->B->B'->C->C' 切割成：A->B->C 和 A'->B'->C'
        Node old = head;
        // KeyPoint 注意：new 是关键词，不能作为变量名，使用 newNode 来代替
        Node newNode = head.next;
        // 克隆链表头节点
        Node cloneHead = head.next;

        while (old != null) {

            //  cloneHead
            //     ↓
            //  newNode
            //     ↓
            //  A->A'->B->B'->C->C'
            //  ↑
            // old

            // A->B->C
            // old.next = old.next.next;
            // 为了严谨性，可以这样写，必然正确，不需要考虑边界情况
            old.next = (old.next != null) ? old.next.next : null;

            // A'->B'->C'
            // newNode 在 C'位置，则 newNode.next.next 空指针异常
            newNode.next = (newNode.next != null) ? newNode.next.next : null;

            // A->A'->B->B'->C->C'

            // KeyPoint 节点 next 指针调整，则下个节点也发生变化
            // 若调整链表 next 指针，则链表后一个节点就已经更新了，不在是原链表的后一个节点
            //  A->A'->B->B'->C->C'
            //  ↑
            // old
            // old.next = A'
            // => 经过 old.next = (old.next != null) ? old.next.next : null;
            // => 后一个节点发生变化
            // => old.next = B

            // A -> B
            old = old.next;
            // A'-> B'
            newNode = newNode.next;
        }
        return cloneHead;
    }
}
