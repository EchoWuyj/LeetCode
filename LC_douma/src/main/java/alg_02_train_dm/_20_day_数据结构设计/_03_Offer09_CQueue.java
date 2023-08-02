package alg_02_train_dm._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:13
 * @Version 1.0
 */


public class _03_Offer09_CQueue {

     /*
        剑指 Offer 09. 用两个栈实现队列
        请用两个栈来实现一个队列，这个队列的实现类中含有两个方法：
        1. appendTail(int value) ：在队列的尾部插入一个整数
        2. deleteHead() ：删除队列头部的元素，并返回删除的元素，如果队列中没有元素的话，则返回 -1

        示例 1：
        输入：
        ["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
        [[],[3],[],[],[]]
        输出：[null,null,3,-1,-1]

        提示：
        1 <= values <= 10000
        最多会对 appendTail、deleteHead 进行 10000 次调用

     */

    // KeyPoint 方法一 两个栈
    class CQueue1 {
        // 主栈
        Deque<Integer> stack1;
        // 辅助栈
        Deque<Integer> stack2;

        public CQueue1() {
            this.stack1 = new ArrayDeque<>();
            this.stack2 = new ArrayDeque<>();
        }

        // 时间复杂度 O(n)
        public void appendTail(int value) {
            // 将 stack2 转移 stack1
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            // stack1 压入元素
            stack1.push(value);
        }

        // 时间复杂度 O(n)
        public int deleteHead() {
            // 将 stack1 转移 stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            // stack2 不为空，则 stack2 pop 元素
            if (stack2.isEmpty()) {
                return -1;
            } else {
                return stack2.pop();
            }
        }
    }

    // KeyPoint 方法二 优化，提高性能
    class CQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue() {
            this.stack1 = new ArrayDeque<>();
            this.stack2 = new ArrayDeque<>();
        }

        // 时间复杂度 O(1)
        public void appendTail(int value) {
            stack1.push(value);
        }

        // 时间复杂度 O(n)
        public int deleteHead() {
            // 1.stack2 为空，则将 stack1 的所有元素都是 push 到 stack2 中
            if (stack2.isEmpty()) {
                // while 循环判断，处理不知道有几次循环的场景
                // 而 if 只能处理单个判断，所以本质并不相同
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            // 2.stack2 不为空，则 stack2 pop 元素
            if (stack2.isEmpty()) {
                return -1;
            } else {
                return stack2.pop();
            }
        }
    }
}
