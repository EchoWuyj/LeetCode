package alg_02_train_dm._15_day_链表二_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 17:02
 * @Version 1.0
 */
public class _06_445_add_two_numbers_ii2 {

    // KeyPoint 方法二 使用栈
    // 链表表头表示数字高位，在数字相加中无法先运算，故先将其暂存，后面再操作
    // => 先存后操作 => 栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 7 → 2 → 4 → 3
        // 5 → 6 → 4

        //   | 3 |
        //   | 4 |      | 4 |
        //   | 2 |      | 6 |
        //   | 7 |      | 5 |
        //  stack1     stack2

        // 栈顶元素为表尾元素，相加运算
        // 3 + 4 = 7
        // 4 + 6 = 0，carry = 1
        // 2 + 5 + carry = 8
        // 7 + 0 = 7

        //     低位        高位
        //      ↓           ↓
        // res：7 → 0 → 8 → 7
        // 题目要求：高位为 head 节点，故需要反转
        // 反转 7 ← 0 ← 8 ← 7

        // 特判
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 将 l1 所有节点放到栈中
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        while (l1 != null) {
            if (l1 != null) stack1.push(l1);
            l1 = l1.next;
        }

        // 将 l2 所有节点放到栈中
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();
        while (l2 != null) {
            if (l2 != null) stack2.push(l2);
            l2 = l2.next;
        }

        // 结果链表头指针
        ListNode res = null;
        // 用于存储两数相加时的进位
        ListNode curr;
        int carry = 0;

        // 只要有一个栈不为空，就循环执行代码
        while (!stack1.isEmpty() || !stack2.isEmpty()) {

            // KeyPoint 凡是有指涉，包括方法调用，需要判空
            // 1.若 stack 为空，pop() 操作会抛出异常 exception，而不是 null
            //   int x = stack1.pop() == null ? 0 : stack1.pop().val;   ×
            //   int x = stack1.isEmpty() ? 0 : stack1.pop().val;       √
            //   => 所以在 pop 操作之前，先进行判空操作， stack1.isEmpty()
            // 2.stack1.pop() 结果是 ListNode，但是要的是 val，不是 ListNode，故需要 stack1.pop().val
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = x + y + carry;

            // 将计算的结果，低位数字，放在链表表头
            // 指针从后往前指向，从而保证高位指向低位，从而避免链表再次反转
            // 最终结果：7 ← 0 ← 8 ← 7

            // res ← 7
            //       ↑
            //      cur
            // res 移动指针 => res = curr

            //      ← 7  ← 0
            //             ↑
            //            cur
            //       ↑
            //      res

            curr = new ListNode(sum % 10);
            curr.next = res;
            res = curr;
            carry = sum / 10;
        }

        if (carry != 0) {
            curr = new ListNode(carry);
            curr.next = res;
            res = curr;
        }
        return res;
    }
}
