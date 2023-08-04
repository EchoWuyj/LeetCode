package alg_02_train_dm._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 9:48
 * @Version 1.0
 */
public class _01_155_MinStack3 {

    // KeyPoint 方法三 一个栈，但保存的是 Node，Node 有两属性 val 和 min
    class MinStack3 {

        // KeyPoint 定义 Node 节点的完整性，{ } 两个都不能丢
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

            // 一开始假设 Node 节点 min 为 val
            int min = val;

            // 1.如果 Stack 为空，跳过 if 判断，则 val 即为 min，此时 Node 是第一个节点
            // 2.凡是涉及方法调用，如 stack.peek() 最好判空下，保证程序的严谨性
            // KeyPoint 小技巧：if 可以替换 while，但是 while 不可以替换 if 的
            if (!stack.isEmpty() && stack.peek().min < val) {
                // 栈顶 Node 节点 min 严格比 val 小，使用 stack.peek().min 赋值 min
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
}
