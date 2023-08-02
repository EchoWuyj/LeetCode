package alg_02_train_dm._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 16:12
 * @Version 1.0
 */


public class _01_155_MinStack {

     /*
        155. 最小栈
        设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

        实现 MinStack 类:
        MinStack() 初始化堆栈对象。
        void push(int val) 将元素 val 推入堆栈。
        void pop() 删除堆栈顶部的元素。
        int top() 获取堆栈顶部的元素。
        int getMin() 获取堆栈中的最小元素。

        示例 1:
        输入：
        ["MinStack","push","push","push","getMin","pop","top","getMin"]
        [[],[-2],[0],[-3],[],[],[],[]]

        输出：
        [null,null,null,null,-3,null,0,-2]

        解释：
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.getMin();   --> 返回 -2.

        提示：
        -2^31 <= val <= 2^31 - 1
        pop、top 和 getMin 操作总是在 非空栈 上调用
        push, pop, top, and getMin最多被调用 3 * 10^4 次

     */

    // KeyPoint 方法一 遍历 stack 比较获取最小值
    class MinStack1 {

        // 推荐使用  private Deque<Integer> stack;
        private Deque<Integer> stack;

        public MinStack1() {
            stack = new ArrayDeque<>();
        }

        public void push(int x) {
            stack.push(x);
        }

        public void pop() {
            // pop 弹栈，有返回 E，只是这里没有 return
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        // O(n) => 不符合在'常数时间内检索到最小元素的栈'
        public int getMin() {

            // KeyPoint 错误写法，不能将 stack 中元素弹栈，否则影响后续操作
            // minValue = Math.min(minValue, stack.poll());

            // 将栈顶元素，默认设置为最小
            // 避免 int minValue = Integer.MAX_VALUE;
            int minValue = stack.peek();

            // 循环遍历 stack 中元素即可，直接将 stack 看成普通集合即可
            for (Integer i : stack) {
                minValue = Math.min(minValue, i);
            }
            return minValue;
        }
    }

    // KeyPoint 方法二 辅助栈 minStack
    class MinStack2 {
        private Deque<Integer> dataStack;
        private Deque<Integer> minStack;

        public MinStack2() {
            dataStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            dataStack.push(val);

            // KeyPoint minStack 压栈 2 种情况
            // 1.minStack 刚开始
            // 2.val <= minStack 栈顶
            // 注意：val = minStack 是包括的，若去掉等于，则可能会出现 dataStack 不为空
            //      但是 minStack 为空了，这样下面的 getMin 就会出现异常了
            // val > minStack.peek() 情况都没有进入 minStack 中
            // KeyPoint 凡是边界条件，一定要好好考虑，特别容易出错
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int top = dataStack.pop();
            // top 为 minStack 的栈顶，才去 pop，否则只是 dataStack 进行 pop
            if (top == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // KeyPoint 方法三 一个栈，但保存的是 Node，Node 有两属性 val 和 min
    class MinStack3 {

        // KeyPoint 定义类的完整性，{ } 两个都不能丢
        class Node {
            // 属性设置为默认的，后续直接通过 . 获取，不需要使用 get 和 set 方法
            int val;
            int min;

            public Node() {
            }

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        // 先将 stack 作为属性定义好，后面在空参构造中再去赋值
        private Deque<Node> stack;

        public MinStack3() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            Node node = new Node();
            node.val = val;

            // 一开始 min 默认为 val
            int min = val;

            // 如果 Stack 为空，跳过 if 判断，则 val 即为 min，此时 Node 是第一个节点
            // 凡是涉及方法调用，如 stack.peek() 最好判空下，保证程序的严谨性
            // KeyPoint 小技巧：if 可以替换 while，但是 while 不可以替换 if 的
            if (!stack.isEmpty() && stack.peek().min < val) {
                // val 严格大于时，才进行赋值操作
                min = stack.peek().min;
            }
            // 求出 min，进行赋值
            node.min = min;
            // 需要将 node 压入栈中
            stack.push(node);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().val;
        }

        public int getMin() {
            return stack.peek().min;
        }
    }

    // KeyPoint 方法四 通过自定义链表实现栈 => 击败了 100.00% 的用户
    // 链表表头 => 栈顶 => push 和 pop 时间复杂度 O(1) 因为存在 dummyHead，表头比较好操作
    // 不使用系统已经有的栈实现，性能最好，速度最快 => 一般自己定义栈比使用系统栈速度要快
    // 类似：双端队列，自己手动实现比调用系统的
    class MinStack4 {
        class ListNode {
            int val;
            int min;
            ListNode next;

            public ListNode() {
            }

            public ListNode(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        // 虚拟头节点
        ListNode dummyHead;

        public MinStack4() {
            // 不使用系统栈
            dummyHead = new ListNode();
        }

        public void push(int x) {
            int min = x;
            // x 和链表表头的值进行比较，取小的值
            ListNode head = dummyHead.next;
            // head.min 调用保证 head 不为 null
            if (head != null && head.min < x) {
                min = head.min;
            }
            ListNode node = new ListNode(x, min);

            // 一般情况和特殊情况分别进行讨论，看代码是否都能满足，从而确定最后的代码
            // 链接 node next 指针
            node.next = dummyHead.next;
            // 设置表头节点
            dummyHead.next = node;
        }

        public void pop() {
            ListNode head = dummyHead.next;
            // 表头不为空 => 栈不为空
            if (head != null) {
                // 删除栈顶元素
                dummyHead.next = head.next;
                head.next = null;
            }
        }

        public int top() {
            return dummyHead.next.val;
        }

        public int getMin() {
            return dummyHead.next.min;
        }
    }
}
